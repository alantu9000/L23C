package lcmine.extra;

import java.util.*;

public class cdmain {

    public static String cd(String cwd, String arg) {
        Deque<String> curDir = new LinkedList<>();
        if ( !arg.startsWith("/")) {
            if (cwd.startsWith("/")) {
                cwd = cwd.substring(1);
            }
            curDir.addAll(Arrays.asList(cwd.split("/")));
        }
        String[] dirs = arg.split("/");
        for (String dir : dirs) {
            if (dir.equals(".") || dir.length() == 0) {
                continue;
            }
            if (dir.equals("..") ) {
                if (curDir.size() > 0 ) {
                    curDir.removeLast();
                }
                continue;
            }
            curDir.addLast(dir);
        }
        return "/" + String.join("/", curDir);
    }

    public static void testCd(String cwd, String arg, String expected) {
        String ret = cd(cwd, arg);
        if (!ret.equals(expected)) {
            System.out.println("expected: " + expected +", ret: " +  ret);
        } else {
            System.out.println("test good ");
        }
    }
    public static void main(String[] args) {
        testCd("/", "a", "/a");
        testCd("/a", "/b", "/b");
        testCd("/lol", "../../../../..", "/");
        testCd("/foo", "bar/../bar/./x", "/foo/bar/x");
        testCd("/x", "/p/../q", "/q");
   

    }
}
