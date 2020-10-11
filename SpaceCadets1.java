/**
 *
 * Code for Space Cadet challenge 1
 *
 * Last edited 17:39 10/11/2020
 *
 * https://secure.ecs.soton.ac.uk/student/wiki/w/COMP1202/Space_Cadets/SCChallengeEmail
 *
 * Have a look at the Web page at https://www.ecs.soton.ac.uk/people/dem. This is a departmental information page which gives all sorts of information about a member of staff. The Web address is constructed from a departmental email id (in this case dem). If I have someone else's email id, I can look up their name from one of these Web pages. Try it with your own email id. In fact, in the past the name started at the 12th character of the 6th line of the HTML data returned by the Web server. It finishes when a '<' character appears. (Choose 'View Source' from your Web browser to check where it is now.)
 *
 * Write a program which converts an email id into a name by
 *
 *     Constructing a BufferedReader object so that can read an email id from System.in (you will need some intermediate objects to help you here. Look it up!)
 *     Constructing the full Web page address by string concatenation
 *     Constructing a URL object from the Web address
 *     Constructing a BufferedReader object that can read from the URL (you will need some intermediate objects to help you here. Look in the book!)
 *     Ignoring the first lines of input from the Web page and saving the one which contains the name (Hint: <... property="name">)
 *     Use the indexOf() and substring() methods to find and extract the name from the line
 *     Print out the result
 *
 */

import java.net.*;
import java.io.*;

class SpaceCadet1 {

        public static void main(String[] args) throws Exception {

            //getting user input
            InputStreamReader r=new InputStreamReader(System.in);
            BufferedReader br=new BufferedReader(r);
            String emailId = br.readLine();


            String content = null;
            URLConnection connection = null;
            try {
                connection =  new URL("https://www.ecs.soton.ac.uk/people/").openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String inputLine;
                String staffName;
                Integer sectionBegin;
                Integer sectionEnd;

                while ((inputLine = in.readLine()) != null) //until end of html code
                    if (inputLine.contains(emailId)) {
                        sectionBegin = inputLine.indexOf(emailId) + emailId.length() + 2; //finds e.g jr1a06">Dr Julian Rathke... .length() is for email ID, + 2 is for the extra "> , starts building the name after that
                        sectionEnd =   inputLine.indexOf("</a></td><td><span class=\"js-tableSort-position\"");
                        staffName = inputLine.substring(sectionBegin, sectionEnd);
                        System.out.println(staffName);
                    }
                in.close();

            }catch ( Exception ex ) {
                ex.printStackTrace();
            }
        }

}