package edu.gatech.seclass.project1;


import java.io.*;

/**
 * Created by bulent.coskun@gatech.edu on 2/8/2016.
 */
public class Core {

    public long TotalWordCount = 0;
    public long TotalSentenceCount = 0;
    public int WordLetterLimit = 4;

    protected String EssayFullPath = "";


    byte[] readBuffer = new byte[1000];
    byte[] delimiters = new byte[]  {';', ':', '.', '!', '?'};

    public void setDelimiters(byte[] delimiters) {
        this.delimiters = delimiters;
    }

    public Core(String essayFullPath) {
        EssayFullPath = essayFullPath;
    }

    public String getError() {
        return Error;
    }

    private String Error = null;
	private BufferedInputStream bis;

    public boolean Analyze() {
        if (EssayFullPath.trim().equals("")) {
            Error = "File name not been provided!";
            return false;
        }

        File essayFile = new File(EssayFullPath);

        try {
            FileInputStream fs = new FileInputStream(essayFile);
            bis = new BufferedInputStream(fs);

            int readCount = bis.read(readBuffer);
            int lastConsecutiveLetterCount = 0;

            int currentLocation = 0;
            boolean delimiterFound;
            int previousSentenceWordCount = 0;


            while (readCount > 0) {

                for (byte i : readBuffer) {
                    currentLocation++;
                    if (currentLocation > readCount) {

                        if (lastConsecutiveLetterCount >= WordLetterLimit) {
                            TotalWordCount++;
                            previousSentenceWordCount++;
                        }

                        if (previousSentenceWordCount > 0)
                            TotalSentenceCount++;

                        break;
                    }

                    if (Character.isWhitespace(i) || i == ' ') {
                        if (lastConsecutiveLetterCount >= WordLetterLimit) {
                            TotalWordCount++;
                            previousSentenceWordCount++;
                        }

                        lastConsecutiveLetterCount = 0;
                        continue;
                    }

                    delimiterFound = false;

                    for (byte d : delimiters) {
                        if (i == d) {
                            delimiterFound = true;
                            break;
                        }
                    }

                    if (!delimiterFound)
                        lastConsecutiveLetterCount++;
                    else {

                        if (lastConsecutiveLetterCount >= WordLetterLimit) {
                            TotalWordCount++;
                            previousSentenceWordCount++;
                        }

                        if (previousSentenceWordCount > 0)
                            TotalSentenceCount++;

                        lastConsecutiveLetterCount = 0;
                        previousSentenceWordCount = 0;
                    }
                }

                readCount = bis.read(readBuffer);
                currentLocation = 0;
            }

            return true;

        } catch (FileNotFoundException e) {
            Error = e.getMessage();
            return false;
        } catch (IOException e) {
            Error = e.getMessage();
            return false;
        }

    }
}
