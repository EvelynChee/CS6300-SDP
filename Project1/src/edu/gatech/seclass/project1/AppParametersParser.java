package edu.gatech.seclass.project1;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bulent.coskun@gatech.edu on 2/8/2016.
 */
public class AppParametersParser {


    private char DelimiterCharacter = '-';

    public AppParametersParser  setDelimiterCharacter(char delimiterCharacter) {
        DelimiterCharacter = delimiterCharacter;
        return this;
    }


    public AppParametersParser() {

    }

    public AppParametersParser( char delimiterCharacter ) {
        DelimiterCharacter = delimiterCharacter;
    }

    private List<String> Params = new ArrayList<String>();
    HashMap<String,String> ParametersKeyValue = new HashMap<String,String>();

    public AppParametersParser AddParam(String param, String defaultValue)
    {
        ParametersKeyValue.put(String.valueOf(DelimiterCharacter) + param, defaultValue);
        Params.add( String.valueOf(DelimiterCharacter) + param );
        return this;
    }

    public String Error  = null;

    public boolean Parse(String[] appArgs)
    {

        String previousParam = "";
        String possibleValue = "";
        for(String param: appArgs )
        {
            if (!previousParam.equals(""))
            {
                if (  param.startsWith( String.valueOf(DelimiterCharacter) ) )
                {
                    previousParam = param;
                    possibleValue = "";
                    ParametersKeyValue.put(param, possibleValue);
                }
                else
                {
                    ParametersKeyValue.put(previousParam, param);
                    possibleValue = "";
                    previousParam = "";
                }
            }
            else if (  param.startsWith( String.valueOf(DelimiterCharacter) ) ) {
                previousParam = param;
                possibleValue = "";
                ParametersKeyValue.put(param, possibleValue);
            }
            else
            {
                ParametersKeyValue.put("-f", param);
            }
        }

        return ValidateArgs();

    }

    private boolean ValidateArgs() {

        for (String key : ParametersKeyValue.keySet()) {
            if ( Params.contains(key) )
            {
                if ( ParametersKeyValue.get(key).trim().equals("") ) {
                	switch (key) {
					case "-f":
						Error = String.format("File name not been provided for analysis!");
						break;

					default:
						Error = String.format("Arguement missing for parameter %s", key );
						break;
					}                    
                    return false;
                } 
                else {
                	switch (key){
                	case "-l":
                		int wordlengthlimit;
                		try {
                            wordlengthlimit=Integer.parseInt(ParametersKeyValue.get(key).trim());
                        } catch (NumberFormatException e) {
                        	Error = String.format("Parameter -l should be used with integer number only");
                        	return false;
                        }
                		if (wordlengthlimit==0) {
                			Error = String.format("Word length limit cannot be 0");
                			return false;
                		}
                	}
                }
            }
            else
            {
                Error = String.format("Unrecognized key %s", key );
                return false;
            }

        }

        return true;
    }
}
