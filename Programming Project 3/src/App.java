import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class App 
{
    public static void main(String[] args) throws Exception 
    {
        final long startTime = System.currentTimeMillis();

        HashMap<Integer, Patient> map = new HashMap<>();
        HashMap<Integer, Patient> mlMap = new HashMap<>();

        String vaxFile1 = "2020VAERSVAX.csv";
        String dataFile1 = "2020VAERSDATA.csv";
        String symptomFile1 = "2020VAERSSYMPTOMS.csv";

        readFiles(vaxFile1, dataFile1, symptomFile1, map);

        System.out.println("Done reading 2020 File");

        String vaxFile2 = "2021VAERSVAX.csv";
        String dataFile2 = "2021VAERSDATA.csv";
        String symptomFile2 = "2021VAERSSYMPTOMS.csv";

        readFiles(vaxFile2, dataFile2, symptomFile2, map);

        System.out.println("Done reading 2021 File");

        String vaxFile3 = "2022VAERSVAX.csv";
        String dataFile3 = "2022VAERSDATA.csv";
        String symptomFile3 = "2022VAERSSYMPTOMS.csv";

        readFiles(vaxFile3, dataFile3, symptomFile3, map);

        System.out.println("Done reading 2022 File");

        String vaxFile4 = "2023VAERSVAX.csv";
        String dataFile4 = "2023VAERSDATA.csv";
        String symptomFile4 = "2023VAERSSYMPTOMS.csv";

        readFiles(vaxFile4, dataFile4, symptomFile4, map);

        System.out.println("Done reading 2023 File");

        String vaxFile5 = "NonDomesticVAERSVAX.csv";
        String dataFile5 = "NonDomesticVAERSDATA.csv";
        String symptomFile5 = "NonDomesticVAERSSYMPTOMS.csv";

        readFiles(vaxFile5, dataFile5, symptomFile5, map);

        System.out.println("Done reading Non Domestic File");

        PrintWriter out = new PrintWriter(new File("VAERS_COVID_DataAugust2023.csv"));

        out.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", "VAERS_ID", "RECVDATE", "STATE", "AGE_YRS", "CAGE_YR", "CAGE_MO", "SEX", "RPT_DATE", "SYMPTOM_TEXT", "DIED", "DATEDIED", "L_THREAT", "ER_VISIT", "HOSPITAL", "HOSPDAYS", "X_STAY", "DISABLE", "RECOVD", "VAX_DATE", "ONSET_DATE", "NUMDAYS", "LAB_DATA", "V_ADMINBY", "V_FUNDBY", "OTHER_MEDS", "CUR_ILL", "HISTORY", "PRIOR_VAX", "SPLTTYPE", "FORM_VERS", "TODAYS_DATE", "BIRTH_DEFECT", "OFC_VISIT", "ER_ED_VISIT", "ALLERGIES", "VAX_TYPE", "VAX_MANU", "VAX_LOT", "VAX_DOSE_SERIES", "VAX_ROUTE", "VAX_SITE", "VAX_NAME", "SYMPTOM1", "SYMPTOMVERSION1", "SYMPTOM2", "SYMPTOMVERSION2", "SYMPTOM3", "SYMPTOMVERSION3", "SYMPTOM4", "SYMPTOMVERSION4", "SYMPTOM5", "SYMPTOMVERSION5");

        for(int id : map.keySet())
        {
            for(int i = 0; i < map.get(id).getVaccines().size(); i++)
            {
                out.printf("\n%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,", map.get(id).getVaersID(), map.get(id).getDate(), map.get(id).getState(), map.get(id).getAgeYrs(), map.get(id).getCageYr(), map.get(id).getCageMo(), map.get(id).getSex(), map.get(id).getRptDate(), map.get(id).getSymptomText(), map.get(id).getDied(), map.get(id).getDateDied(), map.get(id).getLThreat(), map.get(id).getErVisit(), map.get(id).getHospital(), map.get(id).getHospdays(), map.get(id).getXStay(), map.get(id).getDisable(), map.get(id).getRecovd(), map.get(id).getVaxDate(), map.get(id).getOnsetDate(), map.get(id).getNumDays(), map.get(id).getLabData(), map.get(id).getVAdminBy(), map.get(id).getVFundBy(), map.get(id).getOtherMeds(), map.get(id).getCurIll(), map.get(id).getHistory(), map.get(id).getPriorVax(), map.get(id).getSpltType(), map.get(id).getFormVers(), map.get(id).getTodaysDate(), map.get(id).getBirthDefect(), map.get(id).getOfcVisit(), map.get(id).getErEDVisit(), map.get(id).getAllergies(), map.get(id).getVaccine(i).getVaxType(), map.get(id).getVaccine(i).getVaxManu(), map.get(id).getVaccine(i).getVaxLot(), map.get(id).getVaccine(i).getVaxDoseSeries(), map.get(id).getVaccine(i).getVaxRoute(), map.get(id).getVaccine(i).getVaxSite(), map.get(id).getVaccine(i).getVaxName());
            
                for(int j = 0; j < map.get(id).getSymptoms().size(); j++)
                {
                    out.printf("%s,%s,", map.get(id).getSymptom(j).getSymptom(), map.get(id).getSymptom(j).getSymptomVersion());
                }
            }
        }

        out.close();

        String inputFile = "VAERS_COVID_DataAugust2023.csv";
        createMLFile(inputFile, mlMap);

        System.out.println("Done reading August 2023 File");

        PrintWriter mlOut = new PrintWriter(new File("VAERSData_ML.csv"));

        mlOut.printf("%s,%s,%s,%s,%s,%s,%s,%s", "VAERS_ID", "RECVDATE", "AGE_YRS", "SEX", "DIED", "DATEDIED", "VAX_MANU", "SYMPTOMCOUNT");

        for(int id : mlMap.keySet())
        {
            for(int i = 0; i < mlMap.get(id).getVaccines().size(); i++)
            {
                mlOut.printf("\n%s,%s,%s,%s,%s,%s,%s,%s,", mlMap.get(id).getVaersID(), mlMap.get(id).getDate(), mlMap.get(id).getAgeYrs(), mlMap.get(id).getSex(), mlMap.get(id).getDied(), mlMap.get(id).getDateDied(), mlMap.get(id).getVaccine(i).getVaxManu(), mlMap.get(id).getSymptoms().size());
            
                for(int j = 0; j < mlMap.get(id).getSymptoms().size(); j++)
                {
                    mlOut.printf("%s,", mlMap.get(id).getSymptom(j).getSymptom());
                }
            }
        }

        mlOut.close();

        String aprioriFile = "VAERSData_ML.csv";

        apriori(aprioriFile);

        System.out.println("\n" + (System.currentTimeMillis() - startTime) / 60000.0 + " minutes to execute");
    }

    public static void readFiles(String vaxFile, String dataFile, String symptomFile, HashMap<Integer, Patient> myMap)
    {
        BufferedReader vaxFileReader = null;
        String vaxFileLine = "";

        BufferedReader dataFileReader = null;
        String dataFileLine = "";

        BufferedReader symptomFileReader = null;
        String symptomFileLine = "";

        try
        {
            vaxFileReader = new BufferedReader(new FileReader(vaxFile));
            dataFileReader = new BufferedReader(new FileReader(dataFile));
            symptomFileReader = new BufferedReader(new FileReader(symptomFile));

            vaxFileLine = vaxFileReader.readLine();
            vaxFileLine = vaxFileReader.readLine();

            while(vaxFileLine != null)
            {
                String[] vaxRow = vaxFileLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                if(vaxRow[1].contains("COVID19"))
                {
                    if(myMap.containsKey(Integer.parseInt(vaxRow[0])))
                    {
                        Patient curPatient = myMap.get(Integer.parseInt(vaxRow[0]));

                        Vaccine newVaccine = new Vaccine();
                        myMap.get(curPatient.getVaersID()).addVaccine(newVaccine);

                        myMap.get(curPatient.getVaersID()).getVaccine(myMap.get(curPatient.getVaersID()).getVaxCount() - 1).setVaxType(vaxRow[1]);
                        myMap.get(curPatient.getVaersID()).getVaccine(myMap.get(curPatient.getVaersID()).getVaxCount() - 1).setVaxManu(vaxRow[2]);
                        myMap.get(curPatient.getVaersID()).getVaccine(myMap.get(curPatient.getVaersID()).getVaxCount() - 1).setVaxLot(vaxRow[3]);
                        myMap.get(curPatient.getVaersID()).getVaccine(myMap.get(curPatient.getVaersID()).getVaxCount() - 1).setVaxDoseSeries(vaxRow[4]);
                        myMap.get(curPatient.getVaersID()).getVaccine(myMap.get(curPatient.getVaersID()).getVaxCount() - 1).setVaxRoute(vaxRow[5]);
                        myMap.get(curPatient.getVaersID()).getVaccine(myMap.get(curPatient.getVaersID()).getVaxCount() - 1).setVaxSite(vaxRow[6]);
                        myMap.get(curPatient.getVaersID()).getVaccine(myMap.get(curPatient.getVaersID()).getVaxCount() - 1).setVaxName(vaxRow[7]);
                    }

                    else
                    {
                        Patient newPatient = new Patient(Integer.parseInt(vaxRow[0]));
                        myMap.put(newPatient.getVaersID(), newPatient);

                        Vaccine newVaccine = new Vaccine();
                        myMap.get(newPatient.getVaersID()).addVaccine(newVaccine);

                        myMap.get(newPatient.getVaersID()).getVaccine(myMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxType(vaxRow[1]);
                        myMap.get(newPatient.getVaersID()).getVaccine(myMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxManu(vaxRow[2]);
                        myMap.get(newPatient.getVaersID()).getVaccine(myMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxLot(vaxRow[3]);
                        myMap.get(newPatient.getVaersID()).getVaccine(myMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxDoseSeries(vaxRow[4]);
                        myMap.get(newPatient.getVaersID()).getVaccine(myMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxRoute(vaxRow[5]);
                        myMap.get(newPatient.getVaersID()).getVaccine(myMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxSite(vaxRow[6]);
                        myMap.get(newPatient.getVaersID()).getVaccine(myMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxName(vaxRow[7]);
                    }
                }

                vaxFileLine = vaxFileReader.readLine();
            }

            dataFileLine = dataFileReader.readLine();
            dataFileLine = dataFileReader.readLine();

            while(dataFileLine != null)
            {
                String[] dataRow = dataFileLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        
                if(myMap.containsKey(Integer.parseInt(dataRow[0])))
                {
                    int curKey = Integer.parseInt(dataRow[0]);

                    myMap.get(curKey).setDate(dataRow[1]);
                    myMap.get(curKey).setState(dataRow[2]);
                    myMap.get(curKey).setAgeYrs(dataRow[3]);
                    myMap.get(curKey).setCageYr(dataRow[4]);
                    myMap.get(curKey).setCageMo(dataRow[5]);
                    myMap.get(curKey).setSex(dataRow[6]);
                    myMap.get(curKey).setRptDate(dataRow[7]);
                    myMap.get(curKey).setSymptomText(dataRow[8]);
                    myMap.get(curKey).setDied(dataRow[9]);
                    myMap.get(curKey).setDateDied(dataRow[10]);
                    myMap.get(curKey).setLThreat(dataRow[11]);
                    myMap.get(curKey).setErVisit(dataRow[12]);
                    myMap.get(curKey).setHospital(dataRow[13]);
                    myMap.get(curKey).setHospdays(dataRow[14]);
                    myMap.get(curKey).setXStay(dataRow[15]);
                    myMap.get(curKey).setDisable(dataRow[16]);
                    myMap.get(curKey).setRecovd(dataRow[17]);
                    myMap.get(curKey).setVaxDate(dataRow[18]);
                    myMap.get(curKey).setDate(dataRow[19]);
                    myMap.get(curKey).setOnsetDate(dataRow[20]);
                    myMap.get(curKey).setNumDays(dataRow[21]);
                    myMap.get(curKey).setLabData(dataRow[22]);
                    myMap.get(curKey).setVAdminBy(dataRow[23]);
                    myMap.get(curKey).setVFundBy(dataRow[24]);
                    myMap.get(curKey).setOtherMeds(dataRow[25]);
                    myMap.get(curKey).setCurIll(dataRow[26]);
                    myMap.get(curKey).setHistory(dataRow[27]);
                    myMap.get(curKey).setSpltType(dataRow[28]);
                    myMap.get(curKey).setFormVers(dataRow[29]);

                    if(dataRow.length == 30)
                    {
                        myMap.get(curKey).setTodaysDate("");
                        myMap.get(curKey).setBirthDefect("");
                        myMap.get(curKey).setOfcVisit("");
                        myMap.get(curKey).setErEDVisit("");
                        myMap.get(curKey).setAllergies("");
                    }

                    else if(dataRow.length == 31)
                    {
                        myMap.get(curKey).setTodaysDate(dataRow[30]);
                        myMap.get(curKey).setBirthDefect("");
                        myMap.get(curKey).setOfcVisit("");
                        myMap.get(curKey).setErEDVisit("");
                        myMap.get(curKey).setAllergies("");
                    }

                    else if(dataRow.length == 32)
                    {
                        myMap.get(curKey).setTodaysDate(dataRow[30]);
                        myMap.get(curKey).setBirthDefect(dataRow[31]);
                        myMap.get(curKey).setOfcVisit("");
                        myMap.get(curKey).setErEDVisit("");
                        myMap.get(curKey).setAllergies("");
                    }

                    else if(dataRow.length == 33)
                    {
                        myMap.get(curKey).setTodaysDate(dataRow[30]);
                        myMap.get(curKey).setBirthDefect(dataRow[31]);
                        myMap.get(curKey).setOfcVisit(dataRow[32]);
                        myMap.get(curKey).setErEDVisit("");
                        myMap.get(curKey).setAllergies("");
                    }

                    else if(dataRow.length == 34)
                    {
                        myMap.get(curKey).setTodaysDate(dataRow[30]);
                        myMap.get(curKey).setBirthDefect(dataRow[31]);
                        myMap.get(curKey).setOfcVisit(dataRow[32]);
                        myMap.get(curKey).setErEDVisit(dataRow[33]);
                        myMap.get(curKey).setAllergies("");
                    }

                    else
                    {
                        myMap.get(curKey).setTodaysDate(dataRow[30]);
                        myMap.get(curKey).setBirthDefect(dataRow[31]);
                        myMap.get(curKey).setOfcVisit(dataRow[32]);
                        myMap.get(curKey).setErEDVisit(dataRow[33]);
                        myMap.get(curKey).setAllergies(dataRow[34]);
                    }
                }

                dataFileLine = dataFileReader.readLine();
            }

            symptomFileLine = symptomFileReader.readLine();
            symptomFileLine = symptomFileReader.readLine();

            while(symptomFileLine != null)
            {
                String[] symptomRow = symptomFileLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        
                if(myMap.containsKey(Integer.parseInt(symptomRow[0])))
                {
                    for(int i = 1; i < symptomRow.length - 1; i = i + 2)
                    {
                        myMap.get(Integer.parseInt(symptomRow[0])).addSymptom(symptomRow[i], symptomRow[i + 1]);
                    }
                }

                symptomFileLine = symptomFileReader.readLine();
            }

            vaxFileReader.close();
            dataFileReader.close();
            symptomFileReader.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }  

    public static void createMLFile(String inputFile, HashMap<Integer, Patient> myMLMap)
    {
        int curID = 0;
        String curVaxManu = "";
        String curVaxLot = "";
        String curVaxDoseSeries = "";
        String curVaxRoute = "";
        String curVaxSite = "";
        String curVaxName = "";

        BufferedReader fileReader = null;
        String fileLine = "";

        try
        {
            fileReader = new BufferedReader(new FileReader(inputFile));
        
            fileLine = fileReader.readLine();
            fileLine = fileReader.readLine();

            while(fileLine != null)
            {
                String[] row = fileLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                if(Integer.parseInt(row[0]) != curID)
                {
                    Patient newPatient = new Patient(Integer.parseInt(row[0]));
                    myMLMap.put(newPatient.getVaersID(), newPatient);

                    Vaccine newVaccine = new Vaccine();
                    myMLMap.get(newPatient.getVaersID()).addVaccine(newVaccine);

                    myMLMap.get(newPatient.getVaersID()).getVaccine(myMLMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxType(row[35]);
                    myMLMap.get(newPatient.getVaersID()).getVaccine(myMLMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxManu(row[36]);
                    myMLMap.get(newPatient.getVaersID()).getVaccine(myMLMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxLot(row[37]);
                    myMLMap.get(newPatient.getVaersID()).getVaccine(myMLMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxDoseSeries(row[38]);
                    myMLMap.get(newPatient.getVaersID()).getVaccine(myMLMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxRoute(row[39]);
                    myMLMap.get(newPatient.getVaersID()).getVaccine(myMLMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxSite(row[40]);
                    myMLMap.get(newPatient.getVaersID()).getVaccine(myMLMap.get(newPatient.getVaersID()).getVaxCount() - 1).setVaxName(row[41]);

                    curID = Integer.parseInt(row[0]);
                    curVaxManu = row[36];
                    curVaxLot = row[37];
                    curVaxDoseSeries = row[38];
                    curVaxRoute = row[39];
                    curVaxSite = row[40];
                    curVaxName = row[41];

                    myMLMap.get(newPatient.getVaersID()).setDate(row[1]);
                    myMLMap.get(newPatient.getVaersID()).setAgeYrs(row[3]);
                    myMLMap.get(newPatient.getVaersID()).setSex(row[6]);
                    myMLMap.get(newPatient.getVaersID()).setDied(row[9]);
                    myMLMap.get(newPatient.getVaersID()).setDateDied(row[10]);
                    myMLMap.get(newPatient.getVaersID()).setVaxDate(row[18]);
                }

                else
                {
                    if(!(myMLMap.get(Integer.parseInt(row[0])).getVaccine(myMLMap.get(Integer.parseInt(row[0])).getVaxCount() - 1).getVaxManu().equals(curVaxManu)) || !(myMLMap.get(Integer.parseInt(row[0])).getVaccine(myMLMap.get(Integer.parseInt(row[0])).getVaxCount() - 1).getVaxLot().equals(curVaxLot)) || !(myMLMap.get(Integer.parseInt(row[0])).getVaccine(myMLMap.get(Integer.parseInt(row[0])).getVaxCount() - 1).getVaxDoseSeries().equals(curVaxDoseSeries)) || !(myMLMap.get(Integer.parseInt(row[0])).getVaccine(myMLMap.get(Integer.parseInt(row[0])).getVaxCount() - 1).getVaxRoute().equals(curVaxRoute)) || !(myMLMap.get(Integer.parseInt(row[0])).getVaccine(myMLMap.get(Integer.parseInt(row[0])).getVaxCount() - 1).getVaxSite().equals(curVaxSite)) || !(myMLMap.get(Integer.parseInt(row[0])).getVaccine(myMLMap.get(Integer.parseInt(row[0])).getVaxCount() - 1).getVaxName().equals(curVaxName)))
                    {
                        Vaccine newVaccine = new Vaccine();
                        myMLMap.get(Integer.parseInt(row[0])).addVaccine(newVaccine);
                        myMLMap.get(Integer.parseInt(row[0])).getVaccine(myMLMap.get(Integer.parseInt(row[0])).getVaxCount() - 1).setVaxManu(row[36]);
                    }
                }

                for(int i = 42; i < row.length; i = i + 2)
                {
                    myMLMap.get(Integer.parseInt(row[0])).addSymptom(row[i], row[i + 1]);
                }
                
                fileLine = fileReader.readLine();
            }

            fileReader.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static int countChar(String str, char c)
    {
        int count = 0;
        
        for(int i = 0; i < str.length(); i++)
        {    
            if(str.charAt(i) == c)
            {
                count++;
            } 
        }
        
        return count;
    }

    public static HashMap<String, ElementGroup> generateCombinations(HashMap<Integer, ElementGroup> strings, int combinationSize) 
    {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        generateCombinationsHelper(strings, 0, new ArrayList<String>(), combinationSize, result);

        HashMap<String, ElementGroup> myMap = new HashMap<String, ElementGroup>();

        for(ArrayList<String> combination : result) 
        {
            String strGroup = "";

            for(int i = 0; i < combinationSize; i++)
            {
                strGroup += combination.get(i) + ",";
            }

            ElementGroup elemGroup = new ElementGroup(strGroup);
            myMap.put(strGroup, elemGroup);
        }

        return myMap;
    }

    private static void generateCombinationsHelper(HashMap<Integer, ElementGroup> strings, int index, ArrayList<String> current, int combinationSize, ArrayList<ArrayList<String>> result) 
    {
        if(current.size() == combinationSize) 
        {
            result.add(new ArrayList<String>(current));
            return;
        }

        if(index == strings.size()) 
        {
            return;
        }

        current.add(strings.get(index).getElements());
        generateCombinationsHelper(strings, index + 1, current, combinationSize, result);

        current.remove(current.size() - 1);
        generateCombinationsHelper(strings, index + 1, current, combinationSize, result);
    }

    public static void apriori(String inputFile)
    {
        final int NUM_TRANSACTIONS = 1602580;
        
        int minSup = 0;
        int remCount = 0;
        int setCount = 1;

        HashMap<String, ElementGroup> elemList = new HashMap<String, ElementGroup>();
        HashMap<Integer, String> fileList = new HashMap<Integer, String>();
        HashMap<Integer, ElementGroup> remElemList = new HashMap<Integer, ElementGroup>();
        HashMap<String, ElementGroup> elemList2 = new HashMap<String, ElementGroup>();

        BufferedReader fileReader = null;
        String fileLine = "";

        PrintWriter aprioriOut = null;

        try
        {
            aprioriOut = new PrintWriter(new File("VAERS_COVID_DataApriori.txt"));

            fileReader = new BufferedReader(new FileReader(inputFile));
        
            fileLine = fileReader.readLine();

            Scanner scan = new Scanner(System.in);

            System.out.print("Enter MINSUP Threshold: ");
            minSup = scan.nextInt();

            aprioriOut.println("1-Frequent Itemsets:");
            System.out.println("\nGenerating 1-Frequent Itemsets...");

            for(int i = 0; i < NUM_TRANSACTIONS; i++)
            { 
                fileLine = fileReader.readLine();

                String[] elemRow = fileLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                for(int j = 1; j < elemRow.length; j++)
                {   
                    if(!(elemRow[j].equals("")) && !(elemRow[j].equals("-1.0")))
                    {
                        ElementGroup newElem = new ElementGroup(elemRow[j]);

                        if(elemList.containsKey(elemRow[j]))
                        {
                            elemList.get(elemRow[j]).incElemCount();
                        }

                        else
                        {
                            newElem.incElemCount();
                            elemList.put(elemRow[j], newElem);
                        }
                    }
                }

                fileList.put(i, fileLine);
            }

            for(String id : elemList.keySet())
            {
                if(elemList.get(id).getElemCount() >= minSup)
                {
                    System.out.println("{" + elemList.get(id).getElements() + "}: " + elemList.get(id).getElemCount());
                    aprioriOut.println("{" + elemList.get(id).getElements() + "}: " + elemList.get(id).getElemCount());
                    remElemList.put(remCount, elemList.get(id));
                    remCount++;
                }
            }

            System.out.println();
            aprioriOut.println();

            while(remElemList.size() > 1)
            {
                aprioriOut.println(setCount + 1 + "-Frequent Itemsets:");
                System.out.println("Generating " + (setCount + 1) + "-Frequent Itemsets...");

                elemList2 = generateCombinations(remElemList, setCount + 1);

                remElemList.clear();
                remCount = 0;

                for(int i = 0; i < fileList.size(); i++)
                { 
                    String[] lineRow = fileList.get(i).split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    
                    String newPair = "";

                    for(int j = 1; j < lineRow.length; j++)
                    {
                        if(!(lineRow[j].equals("")) && !(lineRow[j].equals("-1.0")))
                        {
                            if(elemList.get(lineRow[j]).getElemCount() >= minSup)
                            {
                                newPair += lineRow[j] + ",";
                                int offset = 1;

                                for(int k = 0; k < setCount; k++)
                                {
                                    if(j + offset + k < lineRow.length)
                                    {
                                        if(!(lineRow[j + offset + k].equals("")) && !(lineRow[j + offset + k].equals("-1.0")) && !(lineRow[j + offset + k].equals(lineRow[j])))
                                        {
                                            if(elemList.get(lineRow[j + offset + k]).getElemCount() >= minSup)
                                            {
                                                newPair += lineRow[j + offset + k] + ",";
                                            }
                                        }
                                    }
                                }

                                if(!(newPair.equals(lineRow[j] + ",")))
                                {
                                    if(elemList2.get(newPair) == null)
                                    {
                                        for(String id : elemList2.keySet())
                                        {
                                            if(id.length() == newPair.length())
                                            {
                                                char idArr[] = id.toCharArray();
                                                char newPairArr[] = newPair.toCharArray();

                                                Arrays.sort(idArr);
                                                Arrays.sort(newPairArr);

                                                String idStr = new String(idArr);
                                                String newPairStr = new String(newPairArr);

                                                if(idStr.equals(newPairStr))
                                                {
                                                    elemList2.get(id).incElemCount();

                                                    break;
                                                }
                                            }
                                        }
                                    }

                                    else
                                    {
                                        elemList2.get(newPair).incElemCount();
                                    }
                                    
                                }
                                
                                newPair = "";
                                offset++;
                            }
                        } 
                    }
                }
                
                for(String id : elemList2.keySet())
                {
                    if(elemList2.get(id).getElemCount() >= minSup)
                    {
                        System.out.println("{" + elemList2.get(id).getElements().substring(0, elemList2.get(id).getElements().length() - 1) + "}: " + elemList2.get(id).getElemCount());
                        aprioriOut.println("{" + elemList2.get(id).getElements().substring(0, elemList2.get(id).getElements().length() - 1) + "}: " + elemList2.get(id).getElemCount());

                        String elements[] = elemList2.get(id).getElements().split(",");
                        
                        for(int i = 0; i < elements.length; i++)
                        {
                            if(!(remElemList.containsValue(elemList.get(elements[i]))))
                            {
                                remElemList.put(remCount, elemList.get(elements[i]));
                                remCount++;
                            }
                        }
                    }
                }

                elemList2.clear();

                setCount++;

                if(remElemList.size() < 1)
                {
                    System.out.println("No more Itemsets to generate");
                }

                System.out.println();
                aprioriOut.println();
            }
            
            scan.close();
            fileReader.close();
            aprioriOut.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
