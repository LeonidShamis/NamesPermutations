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

public class PermutationsStreamplify {

    private final int n;
    private final int k;

    public PermutationsStreamplify(int n, int k) {
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

    // using Java 8 StreamSupport parallel stream for multiple cores
    // http://www.logicbig.com/tutorials/core-java-tutorial/java-util-stream/sequential-vs-parallel/
    // https://www.sourceallies.com/2015/09/java-8-parallel-vs-sequential-stream-comparison/
    public Stream<int[]> parallelStream() {
        return new Combinations(n, k)
                .parallelStream()
                .flatMap(comb -> new Permutations(k)
                        .parallelStream()
                        .map(perm -> Arrays.stream(perm).map(p -> comb[p]).toArray()));
    }

    public static void main(String[] args) {

        /**
         * k-permutations of n names from the input file
         */
        try {

            String fileName = System.getProperty( "file", "23_names.txt" );
            System.out.println("k-permutations of n names from the input file " + fileName );
            System.out.println("press any key to continue or Ctrl^C to exit...");
            System.in.read();

            List<String> namesList = NamesFileUtil.readLines(new File(fileName));
            String[] namesArray = namesList.toArray(new String[0]);

            // with improvement suggested at https://github.com/beryx/streamplify/issues/7#issuecomment-366178625
            System.out.println("Permutations on array length " + namesArray.length + "\n");
            System.out.println("-----------------------------------------------------------------------------------");
            Stream<String> permStream = new PermutationsStreamplify(namesArray.length, 12)
                    .parallelStream()
                    .map(arr -> Arrays.stream(arr).mapToObj(i -> namesArray[i]).collect(Collectors.toList()).toString());
            permStream.forEach(System.out::println);
            System.out.println("-----------------------------------------------------------------------------------");

        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
    }
}
