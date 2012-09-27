package cz.kiml;

/**
 * Class with many problems to be able to test behavior of eclipse/hudson plugins for findbugs, task scanner, ...
 * @author kiml
 *
 */
public class WarningsHelper
{
    // FIXME some fixme
    // TODO some todo
    private int x;
    
    public void testMethod(int q) {
        if (true)
            return;
        int x = 12;
    }

}
