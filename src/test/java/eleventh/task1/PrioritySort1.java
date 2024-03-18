package eleventh.task1;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class PrioritySort1 {
    @Test(groups = "a")
    public void g() { assertTrue(true); }

    @Test(dependsOnGroups = "a", groups = "b")
    public void f() { assertTrue(true); }

    @Test(dependsOnGroups = "b", groups = "c")
    public void e() { assertTrue(true); }

    @Test(dependsOnGroups = "c", groups = "d")
    public void d() { assertTrue(true); }

    @Test(dependsOnGroups = "d", groups = "e")
    public void c() { assertTrue(true); }

    @Test(dependsOnGroups = "e", groups = "f")
    public void b() { assertTrue(true); }

    @Test(dependsOnGroups = "f", groups = "g")
    public void a() { assertTrue(true); }
}
