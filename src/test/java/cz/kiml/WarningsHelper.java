package cz.kiml;

import java.util.HashMap;

/**
 * Class with many problems to be able to test behavior of eclipse/hudson
 * plugins for findbugs, task scanner, ...
 * 
 * @author kiml
 */
public class WarningsHelper
{
    // FIXME some fixme
    // TODO some todo
    private int x;

    public void testMethod(int q)
    {
        int x = 12;
        String person = new HashMap<String, String>().get("A");
        if (person != null)
        {
            person.toCharArray();
        }
        String name = person.toString();
    }

}
