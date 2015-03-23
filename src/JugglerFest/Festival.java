package JugglerFest;

/**
 * Created by panchal on 3/22/15.
 */
public abstract class Festival {

    protected String name;
    protected static int count;
    protected SkillSet skill;

    public abstract void setName(String s);
    public abstract String getName();

    public abstract void setSkillSet(int h, int e, int p);
    public abstract SkillSet getSkillSet();

    ;
}


