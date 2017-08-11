package com.tigerit.exam;


import static com.tigerit.exam.IO.*;
import java.util.Arrays;

/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application's execution points start from inside run method.
 */
public class Solution implements Runnable {

    @Override
    public void run() {
        Integer numberoftestcase, numofqueries[] = new Integer[10], numofresultrow[][] = new Integer[10][50];
        String temp, arr[] = new String[202], queries[][], result[][][] = new String[10][50][10001];
        for (Integer[] resultrow : numofresultrow) {
            Arrays.fill(resultrow, 0);
        }
        for (String[][] results : result) {
            for (String[] res : results) {
                Arrays.fill(res, "");
            }
        }
        numberoftestcase = readLineAsInteger();
        if (numberoftestcase < 1 || numberoftestcase > 10) {
            System.out.println("\nNumber of test case is out of range");
            System.exit(0);
        } else {
            for (int testcasenumber = 0; testcasenumber < numberoftestcase; testcasenumber++) {
                Integer numberoftables = readLineAsInteger();
                if (numberoftables < 2 || numberoftables > 10) {
                    System.out.println("\nIn test case no. " + ++testcasenumber + ", number of tables is out of range");
                    System.exit(0);
                }
                String tablenames[] = new String[numberoftables];
                Integer rows[] = new Integer[numberoftables];
                Integer columns[] = new Integer[numberoftables];
                String columnnames[][] = new String[numberoftables][100];
                String data[][][] = new String[numberoftables][100][100];
                for (int i = 0; i < numberoftables; i++) {
                    tablenames[i] = readLine();
                    temp = readLine();
                    arr = temp.split(" ");
                    columns[i] = Integer.parseInt(arr[0]);
                    if (columns[i] < 2 || columns[i] > 100) {
                        System.out.println("\nIn test case no. " + ++testcasenumber + ",table no ." + ++i + ", column number is out of range");
                        System.exit(0);
                    }
                    rows[i] = Integer.parseInt(arr[1]);
                    if (rows[i] < 2 || rows[i] > 100) {
                        System.out.println("\nIn test case no. " + ++testcasenumber + ",table no ." + ++i + ", row number is out of range");
                        System.exit(0);
                    }
                    temp = readLine();
                    arr = temp.split(" ");
                    System.arraycopy(arr, 0, columnnames[i], 0, columns[i]);
                    for (int j = 0; j < rows[i]; j++) {
                        temp = readLine();
                        arr = temp.split(" ");
                        for (int k = 0; k < columns[i]; k++) {
                            if (Integer.parseInt(arr[k]) < 0 || Integer.parseInt(arr[k]) > 1000000) {
                                System.out.println("\nIn test case no. " + ++testcasenumber + ",table no ." + ++i + ",row no ." + ++j + ",column no ." + ++k + ", " + arr[k - 1] + " data is out of range");
                                System.exit(0);
                            } else {
                                data[i][j][k] = arr[k];
                            }
                        }
                    }
                }
                numofqueries[testcasenumber] = readLineAsInteger();
                if (numofqueries[testcasenumber] < 0 || numofqueries[testcasenumber] > 50) {
                    System.out.println("\nIn test case no. " + ++testcasenumber + ", number of queries is out of range");
                    System.exit(0);
                }
                queries = new String[numofqueries[testcasenumber]][4];
                for (int i = 0; i < numofqueries[testcasenumber]; i++) {
                    for (int j = 0; j < 4; j++) {
                        queries[i][j] = readLine();
                    }
					readLine();                    
                }

                for (int i = 0; i < numofqueries[testcasenumber]; i++) {
                    String table1, table2, table1short, table2short, table1column, table2column, temparray[] = new String[2];
                    Integer table1index, table2index, table1columnindex, table2columnindex;
                    temp = queries[i][0].substring(7);

                    if (temp.compareTo("*") == 0) {
                        arr = queries[i][1].split(" ");
                        if (arr.length == 2) {
                            //System.out.println("sample query 1");
                            table1 = arr[1];
                            arr = queries[i][2].split(" ");
                            table2 = arr[1];
                            arr = queries[i][3].split(" ");
                            temparray = arr[1].split("\\.");
                            if (temparray[0].compareTo(table1) == 0) {
                                table1column = temparray[1];
                                temparray = arr[3].split("\\.");
                                table2column = temparray[1];
                            } else {
                                table2column = temparray[1];
                                temparray = arr[3].split("\\.");
                                table1column = temparray[1];
                            }
                            table1index = Arrays.asList(tablenames).indexOf(table1);
                            table2index = Arrays.asList(tablenames).indexOf(table2);
                            table1columnindex = Arrays.asList(columnnames[table1index]).indexOf(table1column);
                            table2columnindex = Arrays.asList(columnnames[table2index]).indexOf(table2column);
                            for (int j = 0; j < columns[table1index]; j++) {
                                if (j == 0) {
                                    result[testcasenumber][i][numofresultrow[testcasenumber][i]] = columnnames[table1index][j];
                                } else {
                                    result[testcasenumber][i][numofresultrow[testcasenumber][i]] += " " + columnnames[table1index][j];
                                }
                            }
                            for (int j = 0; j < columns[table2index]; j++) {
                                result[testcasenumber][i][numofresultrow[testcasenumber][i]] += " " + columnnames[table2index][j];
                            }
                            numofresultrow[testcasenumber][i]++;
                            for (int j = 0; j < rows[table1index]; j++) {
                                for (int k = 0; k < rows[table2index]; k++) {
                                    if (data[table1index][j][table1columnindex].compareTo(data[table2index][k][table2columnindex]) == 0) {
                                        for (int l = 0; l < columns[table1index]; l++) {
                                            if (l == 0) {
                                                result[testcasenumber][i][numofresultrow[testcasenumber][i]] = data[table1index][j][l];
                                            } else {
                                                result[testcasenumber][i][numofresultrow[testcasenumber][i]] += " " + data[table1index][j][l];
                                            }
                                        }
                                        for (int l = 0; l < columns[table2index]; l++) {
                                            result[testcasenumber][i][numofresultrow[testcasenumber][i]] += " " + data[table2index][k][l];
                                        }
                                        numofresultrow[testcasenumber][i]++;
                                    }
                                }
                            }
                        } else {
                            //System.out.println("sample query 2");
                            table1 = arr[1];
                            table1short = arr[2];
                            arr = queries[i][2].split(" ");
                            table2 = arr[1];
                            table2short = arr[2];
                            arr = queries[i][3].split(" ");
                            temparray = arr[1].split("\\.");
                            if (temparray[0].compareTo(table1short) == 0) {
                                table1column = temparray[1];
                                temparray = arr[3].split("\\.");
                                table2column = temparray[1];
                            } else {
                                table2column = temparray[1];
                                temparray = arr[3].split("\\.");
                                table1column = temparray[1];
                            }
                            table1index = Arrays.asList(tablenames).indexOf(table1);
                            table2index = Arrays.asList(tablenames).indexOf(table2);
                            table1columnindex = Arrays.asList(columnnames[table1index]).indexOf(table1column);
                            table2columnindex = Arrays.asList(columnnames[table2index]).indexOf(table2column);
                            for (int j = 0; j < columns[table1index]; j++) {
                                if (j == 0) {
                                    result[testcasenumber][i][numofresultrow[testcasenumber][i]] = columnnames[table1index][j];
                                } else {
                                    result[testcasenumber][i][numofresultrow[testcasenumber][i]] += " " + columnnames[table1index][j];
                                }
                            }
                            for (int j = 0; j < columns[table2index]; j++) {
                                result[testcasenumber][i][numofresultrow[testcasenumber][i]] += " " + columnnames[table2index][j];
                            }
                            numofresultrow[testcasenumber][i]++;
                            for (int j = 0; j < rows[table1index]; j++) {
                                for (int k = 0; k < rows[table2index]; k++) {
                                    if (data[table1index][j][table1columnindex].compareTo(data[table2index][k][table2columnindex]) == 0) {
                                        for (int l = 0; l < columns[table1index]; l++) {
                                            if (l == 0) {
                                                result[testcasenumber][i][numofresultrow[testcasenumber][i]] = data[table1index][j][l];
                                            } else {
                                                result[testcasenumber][i][numofresultrow[testcasenumber][i]] += " " + data[table1index][j][l];
                                            }
                                        }
                                        for (int l = 0; l < columns[table2index]; l++) {
                                            result[testcasenumber][i][numofresultrow[testcasenumber][i]] += " " + data[table2index][k][l];
                                        }
                                        numofresultrow[testcasenumber][i]++;
                                    }
                                }
                            }
                        }
                    } else {
                        //System.out.println("sample query 3");
                        String selecttabledotcolumn[] = selecttabledotcolumn = temp.split(", ");
                        Integer selectedcolumnswithtableindex[][] = new Integer[200][2];

                        arr = queries[i][1].split(" ");
                        table1 = arr[1];
                        table1short = arr[2];
                        arr = queries[i][2].split(" ");
                        table2 = arr[1];
                        table2short = arr[2];
                        arr = queries[i][3].split(" ");
                        temparray = arr[1].split("\\.");
                        if (temparray[0].compareTo(table1short) == 0) {
                            table1column = temparray[1];
                            temparray = arr[3].split("\\.");
                            table2column = temparray[1];
                        } else {
                            table2column = temparray[1];
                            temparray = arr[3].split("\\.");
                            table1column = temparray[1];
                        }
                        table1index = Arrays.asList(tablenames).indexOf(table1);
                        table2index = Arrays.asList(tablenames).indexOf(table2);
                        table1columnindex = Arrays.asList(columnnames[table1index]).indexOf(table1column);
                        table2columnindex = Arrays.asList(columnnames[table2index]).indexOf(table2column);

                        for (int iterator = 0; iterator < selecttabledotcolumn.length; iterator++) {
                            temparray = selecttabledotcolumn[iterator].split("\\.");
                            if (temparray[0].compareTo(table1short) == 0) {
                                selectedcolumnswithtableindex[iterator][0] = table1index;
                                selectedcolumnswithtableindex[iterator][1] = Arrays.asList(columnnames[table1index]).indexOf(temparray[1]);;
                            } else {
                                selectedcolumnswithtableindex[iterator][0] = table2index;
                                selectedcolumnswithtableindex[iterator][1] = Arrays.asList(columnnames[table2index]).indexOf(temparray[1]);;
                            }
                        }

                        for (int j = 0; j < selecttabledotcolumn.length; j++) {
                            if (result[testcasenumber][i][numofresultrow[testcasenumber][i]] == "") {
                                result[testcasenumber][i][numofresultrow[testcasenumber][i]] = columnnames[selectedcolumnswithtableindex[j][0]][selectedcolumnswithtableindex[j][1]];
                            } else {
                                result[testcasenumber][i][numofresultrow[testcasenumber][i]] += " " + columnnames[selectedcolumnswithtableindex[j][0]][selectedcolumnswithtableindex[j][1]];
                            }
                        }
                        numofresultrow[testcasenumber][i]++;

                        for (int j = 0; j < rows[table1index]; j++) {
                            for (int k = 0; k < rows[table2index]; k++) {
                                if (data[table1index][j][table1columnindex].compareTo(data[table2index][k][table2columnindex]) == 0) {
                                    for (int l = 0; l < selecttabledotcolumn.length; l++) {
                                        if (result[testcasenumber][i][numofresultrow[testcasenumber][i]] == "") {
                                            if (selectedcolumnswithtableindex[l][0] == table1index) {
                                                result[testcasenumber][i][numofresultrow[testcasenumber][i]] = data[table1index][j][selectedcolumnswithtableindex[l][1]];
                                            } else {
                                                result[testcasenumber][i][numofresultrow[testcasenumber][i]] = data[table2index][k][selectedcolumnswithtableindex[l][1]];
                                            }
                                        } else {
                                            if (selectedcolumnswithtableindex[l][0] == table1index) {
                                                result[testcasenumber][i][numofresultrow[testcasenumber][i]] += " " + data[table1index][j][selectedcolumnswithtableindex[l][1]];
                                            } else {
                                                result[testcasenumber][i][numofresultrow[testcasenumber][i]] += " " + data[table2index][k][selectedcolumnswithtableindex[l][1]];
                                            }
                                        }
                                    }
                                    numofresultrow[testcasenumber][i]++;
                                }
                            }
                        }
                    }
                }
            }
        }
        //print result
        for (int iterator = 1; iterator <= numberoftestcase; iterator++) {
            System.out.println("Test: " + iterator);
            for (int i = 0; i < numofqueries[iterator - 1]; i++) {
                Arrays.sort(result[iterator - 1][i], 1, numofresultrow[iterator - 1][i]);
                for (int j = 0; j < numofresultrow[iterator - 1][i]; j++) {
                    System.out.println(result[iterator - 1][i][j]);
                }
                System.out.println();
            }
        }
    }
}
