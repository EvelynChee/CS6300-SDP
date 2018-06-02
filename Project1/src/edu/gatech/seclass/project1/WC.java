package edu.gatech.seclass.project1;


public class WC {

    public static void main(String[] args) {

        AppParametersParser appParameters = new AppParametersParser();

        boolean result = appParameters
                            .setDelimiterCharacter('-')
                            .AddParam("d",":?!;.")
                            .AddParam("l","4")
                            .AddParam("f","")
                            .Parse(args);

        byte[] delimiters = null;
        String fullFilePath =null;
        int wordLetterLength = -1;

        if ( result ) {
            delimiters = appParameters.ParametersKeyValue.get("-d").getBytes();
            fullFilePath = appParameters.ParametersKeyValue.get("-f");
            wordLetterLength = Integer.parseInt(appParameters.ParametersKeyValue.get("-l"));        
        }
        else
        {
            System.err.format("ERROR: %s\n", appParameters.Error);
            return;
        }

        try {
            Core wc = new Core(fullFilePath);
            wc.setDelimiters(delimiters);
            wc.WordLetterLimit = wordLetterLength;
            if (wc.Analyze()) {


            } else {
                System.err.format("ERROR :%s\n", wc.getError());
            }

        } catch (Exception ex) {
            System.err.println("ERROR: " + ex.getMessage());
        }

    }
}
