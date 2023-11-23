public class SoundClip
{
    private double[] clip;
    
    public SoundClip()
    {
        clip = new double[0];
    }
    public SoundClip(double[] arr)
    {
        clip = arr;
    }
    public void adjustVolume(double factor)
    {
        for(int i = 0; i < clip.length; i++)
        {
            clip[i] *= factor; 
        }
    }
    public void mix(double[] clip1, double[] clip2)
    {
        clip = new double[clip1.length];
        for(int i = 0; i < clip1.length; i++)
        {
            clip[i] = clip1[i] + clip2[i];
        }
    }
    public void append(double[] other)
    {
        int sumOfLength = other.length + clip.length;
        double[] result = new double[sumOfLength];
        for(int i = 0; i < clip.length; i++)
        {
            result[i] = clip[i];
        }
        for(int i = clip.length -1; i < sumOfLength; i++)
        {
            for(int j = 0; j < other.length; j++)
            {
                result[i] = other[j];
            }
        }
        
    }
    public void fadeIn(double seconds)
    {
        int size = Sound.toNumSamples(seconds);
        for(int i = 0; i < clip.length; i++)
        {
            clip[i] = clip[i] * (double) (i/size);
        }
    }
    public void fadeOut(double seconds)
    {
        fadeIn(seconds);
        reverse();
    }
    public void reverse()
    {
        for(int i = clip.length-1; i >= 0; i--)
        {
            for(int j = 0; j < clip.length; j++)
            {
                clip[j] = clip[i];
            }
        }
    }
    public void speedUp(double factor)
    {
        SoundClip s = new SoundClip(Sound.pureTone(300, 4/factor));
        Sound.show(s.clip);
        Sound.play(s.clip);
    }
    public double[] getClip()
    {
        return clip;
    }
}
