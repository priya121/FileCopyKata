import java.io.*;

public class FileCopyKata {


    public String read(String inputFilePath, int amountOfBytes) throws IOException {
        File inputFile = new File(inputFilePath);
        InputStream input = new FileInputStream(inputFile);
        byte[] buffer = new byte[amountOfBytes];

        input.read(buffer);

        return new String(buffer);
    }

    public void write(String destinationFilePath, byte[] buffer) throws IOException {
        OutputStream output = new FileOutputStream(new File(destinationFilePath));

        output.write(buffer);
    }


    public void transfer(String inputFilePath, String destinationFilePath, int amountOfBytes) throws IOException {
        File inputFile = new File(inputFilePath);
        InputStream input = new FileInputStream(inputFile);
        OutputStream output = new FileOutputStream(new File(destinationFilePath));
        byte[] buffer = new byte[amountOfBytes];

        int data = input.read(buffer);

        if (data > 0) {
            output.write(buffer);
        }

        int data2 = input.read(buffer);
        if (data > 0) {
            output.write(buffer);
        }
    }
}
