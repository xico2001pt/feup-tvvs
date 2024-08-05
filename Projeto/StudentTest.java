package org.apache.commons.cli;

import junit.framework.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Comparator;

public class StudentTest extends TestCase {
    private static final String EOL = System.getProperty("line.separator");
    private static PrintStream originalOut = System.out;

    private HelpFormatter hf;
    private ByteArrayOutputStream outputStream;

    protected void setUp() throws Exception{
        // Create HelpFormatter instance
        hf = new HelpFormatter();
        
        // Redirect System.out to the captureStream
        outputStream = new ByteArrayOutputStream();
        PrintStream captureStream = new PrintStream(outputStream);
        System.setOut(captureStream);
    }

    protected void tearDown() throws Exception{
        // Restore System.out
        System.setOut(originalOut);
    }

    // Kills mutant 2cf97b70
    public void testDescPadding() throws Exception {
        int descPadding = 10;
        hf.setDescPadding(descPadding);
        assertEquals("descPadding", descPadding, hf.getDescPadding());
    }

    // Kills mutant 22c38749
    public void testNewLine() throws Exception {
        String newLine = "\n\n";
        hf.setNewLine(newLine);
        assertEquals("newLine", newLine, hf.getNewLine());
    }

    // Kills mutant bea79cd2
    public void testOptionComparator() throws Exception {
        Comparator currentComparator = hf.getOptionComparator();
        Comparator newComparator = null;
        hf.setOptionComparator(newComparator);
        if (hf.getOptionComparator() == currentComparator)
            fail("optionComparator is null");
    }

    // Kills mutants d97a5bad and 87b3dae0
    public void testPrintHelpAutoUsageIsFalse() throws Exception {
        String header = EOL + "Header";
        String footer = "Footer";
        Options options = new Options();
        options.addOption(new Option("a", "aaa", false, "aaaaaaa"));
        hf.printHelp(80, "foobar", header, options, footer);
        assertEquals(
                "usage: foobar" + EOL + 
                EOL +
                "Header" + EOL + 
                " -a,--aaa   aaaaaaa" + EOL +
                "Footer" + EOL,
                outputStream.toString());
    }

    // Kills mutants 04dd547b and 998fe9eb
    public void testPrintHelpCharHeaderAndFooter() throws Exception {
        String header = EOL + "H";
        String footer = "F";
        Options options = new Options();
        options.addOption(new Option("a", "aaa", false, "aaaaaaa"));
        hf.printHelp(80, "foobar", header, options, footer);
        assertEquals(
                "usage: foobar" + EOL + 
                EOL +
                "H" + EOL + 
                " -a,--aaa   aaaaaaa" + EOL +
                "F" + EOL,
                outputStream.toString());
    }

    private static class HelpFormatterPrintUsage extends HelpFormatter {
        private boolean check = false;
        private String expected;

        public HelpFormatterPrintUsage(String expected) {
            this.expected = expected;
        }

        public boolean getCheck() {
            return check;
        }

        public void printWrapped(PrintWriter pw, int width, int nextLineTabStop, String text) {
            if (text.equals(expected))
                check = true;
        }
    }

    // Kills mutant 62aa6bd9
    public void testPrintUsage() throws Exception {
        hf = new HelpFormatterPrintUsage("usage: a [-a] [-b]");
        Options options = new Options();
        options.addOption(new Option("a", "aaa", false, "aaa"));
        options.addOption(new Option("b", "bbb", false, "bbb"));
        hf.printUsage(null, 0, "a", options);
        assertTrue(((HelpFormatterPrintUsage) hf).getCheck());
    }

    // Kills mutant 88840c97
    public void testGroupOptionSort() throws Exception {
        OptionGroup group = new OptionGroup();
        group.addOption(OptionBuilder.create("b"));
        group.addOption(OptionBuilder.create("a"));
        group.addOption(OptionBuilder.create("c"));

        Options options = new Options();
        options.addOptionGroup(group);

        hf.setOptionComparator(new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                // reverses the fuctionality of the default comparator
                Option opt1 = (Option) o1;
                Option opt2 = (Option) o2;
                return opt2.getKey().compareToIgnoreCase(opt1.getKey());
            }
        });
        
        PrintWriter printWriter = new PrintWriter(System.out);
        printWriter.flush();
        hf.printUsage(printWriter, 80, "app", options);
        printWriter.close();
        assertEquals("usage: app [-c | -b | -a]" + EOL, outputStream.toString());
    }

    // Kills mutant 3d56cf0e
    public void testEmptyArg() throws Exception {
        Options options = new Options();
        Option option = new Option("a", "aaa");
        option.setArgName("");
        options.addOption(option);

        PrintWriter printWriter = new PrintWriter(System.out);
        printWriter.flush();
        hf.printUsage(printWriter, 80, "app", options);
        printWriter.close();

        assertEquals("usage: app [-a]" + EOL, outputStream.toString());
    }

    // Kills mutants 9da9a743, e0ddeb46 and b8edf1ef
    public void testArgPos() throws Exception {
        PrintWriter printWriter = new PrintWriter(System.out);
        printWriter.flush();
        hf.printUsage(printWriter, 15, "abcdefghij kl");
        printWriter.close();

        assertEquals("usage:" + EOL + " abcdefghij kl" + EOL, outputStream.toString());
    }

    // Kills mutants 0e24cd41, a1588433, ccd34e0b and bc639f59
    public void testEmptyArgName() throws Exception {
        Options options = new Options();
        Option option = new Option("a", "aaa");
        option.setArgName("");
        option.setArgs(1);
        options.addOption(option);

        StringBuffer sb = new StringBuffer();
        hf.renderOptions(sb, 80, options, 2, 2);

        assertEquals("  -a   aaa", sb.toString());
    }

    // Kills mutant 388178fc
    public void testNullOptionDescription() throws Exception {
        Options options = new Options();
        Option option = new Option("a", null);
        options.addOption(option);

        StringBuffer sb = new StringBuffer();
        hf.renderOptions(sb, 80, options, 2, 2);

        assertEquals("  -a", sb.toString());
    }

    // Kills mutant 94ae9050
    public void testWrappedText1() throws Exception {
        int width = 7;
        int padding = 0;
        String text = "Th\nisisatest.";
        String expected = "Th" + EOL + "isisate" + EOL + "st.";
        
        StringBuffer sb = new StringBuffer();
        hf.renderWrappedText(sb, width, padding, text);
        assertEquals(expected, sb.toString());
    }
    
    public void testWrappedText2() throws Exception {
        int width = 4;
        int padding = 3;
        String text = "Th ssss i s";
        String expected = "Th" + EOL + "   s" + EOL + "   s" + EOL + "   s" + EOL + "   s" + EOL + "   i" + EOL + "   s";
        
        StringBuffer sb = new StringBuffer();
        hf.renderWrappedText(sb, width, padding, text);
        assertEquals(expected, sb.toString());
    }

    // Kills mutants c257ca3d, 9a653f3f, 203ed53d and 6e0e3e63
    public void testWrappedPos1() throws Exception {
        int width = 4;
        int startPos = 0;
        String text = "This\n";

        int expected = 5;
        int actual = hf.findWrapPos(text, width, startPos);
        assertEquals(expected, actual);
    }

    // Kills mutants d1591e75 and f182beee
    public void testWrappedPos2() throws Exception {
        int width = 2;
        int startPos = 0;
        String text = "This\n";

        int expected = 2;
        int actual = hf.findWrapPos(text, width, startPos);
        assertEquals(expected, actual);
    }

    // Kills mutants bea8923e and 985755a8
    public void testWrappedPos3() throws Exception {
        int width = -2;
        int startPos = 0;
        String text = "This";

        int expected = -2;
        int actual = hf.findWrapPos(text, width, startPos);
        assertEquals(expected, actual);
    }

    // Kills mutants 19b28ebf and d4bd3f3b
    public void testWrappedPos4() throws Exception {
        int width = 6;
        int startPos = 0;
        String text = "This\t";

        int expected = 5;
        int actual = hf.findWrapPos(text, width, startPos);
        assertEquals(expected, actual);
    }

    // Kills mutant 7ceab697
    public void testWrappedPos5() throws Exception {
        int width = 4;
        int startPos = 0;
        String text = "This\t";

        int expected = 5;
        int actual = hf.findWrapPos(text, width, startPos);
        assertEquals(expected, actual);
    }

    // Kills mutants 66f0a43e and 637a1869
    public void testWrappedPos6() throws Exception {
        int width = 2;
        int startPos = 0;
        String text = "This\t";

        int expected = 2;
        int actual = hf.findWrapPos(text, width, startPos);
        assertEquals(expected, actual);
    }

    // Kills mutants 1d7511cd, 524814bc and 45d4cb15
    public void testWrappedPos7() throws Exception {
        int width = 5;
        int startPos = 0;
        String text = "Th   " + (char)8 + "is";

        int expected = 4;
        int actual = hf.findWrapPos(text, width, startPos);
        assertEquals(expected, actual);
    }

    // Kills mutants a6efaae1 and 5de5b88a
    public void testWrappedPos8() throws Exception {
        int width = 5;
        int startPos = 5;
        String text = "aaa    \naaa";

        int expected = 7;
        int actual = hf.findWrapPos(text, width, startPos);
        assertEquals(expected, actual);
    }

    // Kills mutants b8daeb6f and 7e553acd
    public void testWrappedPos9() throws Exception {
        int width = 5;
        int startPos = 5;
        String text = "aaa    \raaa";

        int expected = 7;
        int actual = hf.findWrapPos(text, width, startPos);
        assertEquals(expected, actual);
    }

    // Kills mutant d960d87a
    public void testWrappedPos10() throws Exception {
        int width = 1;
        int startPos = 6;
        String text = "aaaa   a";

        int expected = 7;
        int actual = hf.findWrapPos(text, width, startPos);
        assertEquals(expected, actual);
    }
}
