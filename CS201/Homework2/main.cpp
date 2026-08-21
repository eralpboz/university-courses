#include <iostream>
#include <string>
#include <ctime>
#include <fstream> 
#include "WizardPotionInventorySystem.h"

using namespace std;
/*
Name: Eralp Yigit Boz
Student Number: 22403188
Section: 2
Cs 201 Homework 2 (Optimized)
*/
int main() {

    int dataPoints[] = {100, 2000, 5000, 15000, 25000, 40000, 55000, 70000, 85000, 100000};
    int numPoints = 10;

    ofstream outFile("results_optimized.csv", ios::app);
    outFile << "N,Add_Time_ms,ShowAll_Time_ms,ShowSingle_Time_ms,Remove_Time_ms\n";


    ofstream devNull("/dev/null"); 
    streambuf* oldCout = cout.rdbuf(devNull.rdbuf()); 

    for (int i = 0; i < numPoints; i++) {
        int N = dataPoints[i];

        cerr << "-> Final Tests Beginning for N = " << N << endl; 
        
        WizardPotionInventorySystem sys;
        
        // 1. addStudentWizard Test
        clock_t startTimeAdd = clock(); 
        for (int j = 0; j < N; j++) {
            sys.addStudentWizard("Wiz" + to_string(j), "Gryffindor"); 
        }
        clock_t endTimeAdd = clock();
        double addDuration = 1000.0 * double(endTimeAdd - startTimeAdd) / CLOCKS_PER_SEC; 

        // 2. showAllStudentWizards Test
        clock_t startTimeShowAll = clock();
        sys.showAllStudentWizards();
        clock_t endTimeShowAll = clock();
        double showAllDuration = 1000.0 * double(endTimeShowAll - startTimeShowAll) / CLOCKS_PER_SEC;

        // 3. showStudentWizard Test
        string midWiz = "Wiz" + to_string(N / 2); 
        clock_t startTimeShowSingle = clock();
        sys.showStudentWizard(midWiz);
        clock_t endTimeShowSingle = clock();
        double showSingleDuration = 1000.0 * double(endTimeShowSingle - startTimeShowSingle) / CLOCKS_PER_SEC;

        // 4. removeStudentWizard Test
        clock_t startTimeRemove = clock();
        sys.removeStudentWizard(midWiz);
        clock_t endTimeRemove = clock();
        double removeDuration = 1000.0 * double(endTimeRemove - startTimeRemove) / CLOCKS_PER_SEC;

        outFile << N << "," << addDuration << "," << showAllDuration << "," << showSingleDuration << "," << removeDuration << endl;
        
        cerr << "   Tests completed for N= " << N << "\n" << endl;
    }

    cout.rdbuf(oldCout); 
    outFile.close();
    cerr << "File is ready." << endl;
    
    return 0;
}