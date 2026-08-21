#ifndef WIZARDPOTIONINVENTORYSYSTEM_H
#define WIZARDPOTIONINVENTORYSYSTEM_H

#include <string>
using namespace std;

/*
Name: Eralp Yigit Boz
Student Number: 22403188
Section: 2
Cs 201 Homework 2 (Optimized)
*/
struct Potion {
    string potionName;
    int strength;
};

struct StudentWizard {
    string name;
    string house;
    Potion* potions;     
    int potionCount;
    int potionCapacity;  
};

class WizardPotionInventorySystem {
public:
    WizardPotionInventorySystem();
    ~WizardPotionInventorySystem();

    void addStudentWizard(const string name, const string house);
    void removeStudentWizard(const string name);

    void brewPotion(const string studentName, const string potionName, const int strength);
    void discardPotion(const string studentName, const string potionName);
    void transferPotion(const string potionName, const string fromStudent, const string toStudent);

    void showAllStudentWizards() const;
    void showStudentWizard(const string name) const;
    void showPotion(const string potionName) const;

private:
    StudentWizard* wizardList; 
    int wizardCount;  
    int wizardCapacity;  
    
   
    void merge(const StudentWizard** arr, const StudentWizard** workspace, int left, int mid, int right) const;
    void mergeSort(const StudentWizard** arr, const StudentWizard** workspace, int left, int right) const;

    int findWizardIndex(const string& name) const;
    int findPotionIndex(int wizardIdx, const string& potionName) const;
};

#endif