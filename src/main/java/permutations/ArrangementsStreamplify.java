// based on https://github.com/beryx/streamplify/blob/master/streamplify-examples/src/main/java/org/beryx/streamplify/example/Arrangements.java
// answer to https://github.com/beryx/streamplify/issues/7
package permutations;

import org.beryx.streamplify.combination.Combinations;
import org.beryx.streamplify.permutation.Permutations;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrangementsStreamplify {

    private final int n;
    private final int k;

    public ArrangementsStreamplify(int n, int k) {
        this.n = n;
        this.k = k;
    }

    public Stream<int[]> stream() {
        return new Combinations(n, k)
                .stream()
                .flatMap(comb -> new Permutations(k)
                        .stream()
                        .map(perm -> Arrays.stream(perm).map(p -> comb[p]).toArray()));
    }

    public Stream<int[]> parallelStream() {
        return new Combinations(n, k)
                .parallelStream()
                .flatMap(comb -> new Permutations(k)
                        .parallelStream()
                        .map(perm -> Arrays.stream(perm).map(p -> comb[p]).toArray()));
    }

    public static void main(String[] args) {

        /**
         * Demonstrates solution for the following problem:<pre>
         * Alice, Bob, Chloe, David, and Emma take part in a competition.
         * List all possible outcomes for the top 3 ranking.</pre>
         */
        String[] names = {"Alice", "Bob", "Chloe", "David", "Emma"};
        System.out.println(new ArrangementsStreamplify(5, 3)
                .stream()
                .map(arr -> Arrays.stream(arr).mapToObj(i -> names[i]).collect(Collectors.toList()).toString())
                .collect(Collectors.joining("\n")));

        System.out.println("\n-----------------------------------------------------------------------------------\n\n");

        /**
         * k-permutations of n names from the input file
         */
        try {

            String fileName = System.getProperty( "file", "23_names.txt" );   // get user name or use 'unknown'
            System.out.println("k-permutations of n names from the input file " + fileName );
            System.out.println("press any key to continue or Ctrl^C to exit...");
            System.in.read();

            List<String> namesList = NamesFileUtil.readLines(new File(fileName));
            String[] namesArray = namesList.toArray(new String[0]);

            System.out.println("Permutations on array length " + namesArray.length + "\n");
            System.out.println(new ArrangementsStreamplify(namesArray.length, 5)
                    .stream()
                    .map(arr -> Arrays.stream(arr).mapToObj(i -> namesArray[i]).collect(Collectors.toList()).toString())
                    .collect(Collectors.joining("\n")));

            System.out.println("-----------------------------------------------------------------------------------");

        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
    }
}
