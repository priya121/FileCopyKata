import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class FileCopyKataTest {

    private FileCopyKata fileCopyKata;


    @Before
    public void setUp() {

        fileCopyKata = new FileCopyKata();
    }

    @Test
    public void readsFile() throws IOException {
        assertEquals("hello", fileCopyKata.read("/Users/priyapatil/IdeaProjects/FileCopyKata/newHello.txt", 5));
    }

    @Test
    public void readsFullFile() throws IOException {
        String inputFilePath = "/Users/priyapatil/IdeaProjects/FileCopyKata/newHello.txt";
        File inputFile = new File(inputFilePath);
        InputStream input;
        input = new FileInputStream(inputFile);

        byte[] bytesRead = new byte[10];

        int amountOfBytes = input.read(bytesRead);

        assertThat(bytesRead, is(new byte[]{
                'h', 'e', 'l', 'l', 'o', 10, 0, 0, 0, 0
        }));
        assertThat(amountOfBytes, is(6));
    }

    @Test
    public void writesTextToAFile() throws IOException {
        String destinationFilePath = "/Users/priyapatil/IdeaProjects/FileCopyKata/helloWorld.txt";
        String inputFilePath = "/Users/priyapatil/IdeaProjects/FileCopyKata/newHello.txt";
        fileCopyKata.write(destinationFilePath, new byte[]{104, 101, 108, 108, 111});
        assertEquals("hello", fileCopyKata.read(destinationFilePath, 5));
    }


    @Test
    public void readsLargerFile() throws IOException {
        String text = "On the face of things, we seem to be merely talking about text-based files, containing only the letters of the English Alphabet (and the occasional punctuation mark). On deeper inspection, of course, this isn't quite the case. What this site offers is a glimpse into the history of writers and artists bound by the 128 characters that the American Standard Code for Information Interchange (ASCII) allowed them. The focus is on mid-1980's textfiles and the world as it was then, but even these files are sometime retooled 1960s and 1970s works, and offshoots of this culture exist to this day";
        assertEquals(text, fileCopyKata.read("/Users/priyapatil/IdeaProjects/FileCopyKata/webtext.txt", 592));
    }

    @Test
    public void readsThenWritesTextAcross() throws IOException {
        String inputFilePath = "/Users/priyapatil/IdeaProjects/FileCopyKata/hello.txt";
        String destinationFilePath = "/Users/priyapatil/IdeaProjects/FileCopyKata/hellooWorld.txt";
        fileCopyKata.transfer(inputFilePath, destinationFilePath, 18);
        assertEquals("hello how are you?", fileCopyKata.read(destinationFilePath, 18));
    }

    @Test
    public void readsAcrossACertainAmountOfBytesAtATime() throws IOException {
        String inputFilePath = "/Users/priyapatil/IdeaProjects/FileCopyKata/hello.txt";
        String destinationFilePath = "/Users/priyapatil/IdeaProjects/FileCopyKata/hellooWorld.txt";
        fileCopyKata.transfer(inputFilePath, destinationFilePath, 9);
        assertEquals("hello how are you?", fileCopyKata.read(destinationFilePath, 18));
    }
}