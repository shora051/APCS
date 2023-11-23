import java.util.*;
public class Runner
{
    public static void main(String[] args)
    {
        SoundLabProbs tester = new SoundLabProbs();
        System.out.println(tester.lastIndexOf(new int[] {8, 2, 4, 6, 8}, 8));
        System.out.println(tester.range(new int[] {8, 3, 5, 7, 2, 4}));
        System.out.println(tester.minDifference(new int[] {4, 8, 6, 1, 5, 9, 4}));
        System.out.println(tester.reverseWords("the sky is blue"));
        System.out.println(tester.priceIsRight(new int[] {900, 885, 990, 1}, 800));
        System.out.println(Arrays.toString(tester.productExceptSelf(new int[] {1, 2, 3, 4})));
        
        //Sound Lab
        //double[] clip = {0.5, 1, 0, -1};
        //Sound.show(clip);
        //Sound.play(clip);
        double[] clip = Sound.pureTone(3.0, 1.0);
        Sound.show(clip);
        Sound.play(clip);
    }
}
