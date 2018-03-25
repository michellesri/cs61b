package hw3.hash;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    /* This should pass if your OomageTestUtility.haveNiceHashCodeSpread
       is correct. This is true even though our given ComplexOomage class
       has a flawed hashCode. */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    @Test
    public void testWithDeadlyParams() {
        List<Oomage> deadlyList = new ArrayList<>();

        deadlyList.add(newComplexOomage(0, 0, 0, 0, 0, 0, 0));
        deadlyList.add(newComplexOomage(1, 0, 0, 0, 0, 0, 0));
        deadlyList.add(newComplexOomage(1, 16, 16, 0, 0, 0, 0));
        deadlyList.add(newComplexOomage(16, 16, 0, 0, 0, 0, 0));
        deadlyList.add(newComplexOomage(1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0));

        for (Oomage oomage : deadlyList) {
            System.out.println(oomage.hashCode());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(deadlyList, 10));
    }


    private ComplexOomage newComplexOomage(int... args) {
        ArrayList<Integer> params = new ArrayList<>(args.length);
        for (int i = 0; i < args.length; i += 1) {
            params.add(args[i]);
        }
        return new ComplexOomage(params);
    }

    /**
     * Calls tests for SimpleOomage.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
