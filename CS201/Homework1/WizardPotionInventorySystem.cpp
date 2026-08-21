#include "WizardPotionInventorySystem.h"
#include <iostream>
/*
Name: Eralp Yigit Boz
Student Number: 22403188
Section: 2
Cs 201 Homework 1
*/
using namespace std;
WizardPotionInventorySystem::WizardPotionInventorySystem()
{
    wizardList = nullptr;
    wizardCount = 0;
}

WizardPotionInventorySystem::~WizardPotionInventorySystem()
{

    if (wizardList != nullptr)
    {

        for (int i = 0; i < wizardCount; i++)
        {

            if (wizardList[i].potions != nullptr)
            {

                delete[] wizardList[i].potions;
            }
        }
        delete[] wizardList;
    }
}

int WizardPotionInventorySystem::findWizardIndex(const string &name) const
{
    for (int i = 0; i < wizardCount; i++)
    {

        if (wizardList[i].name == name)
        {

            return i;
        }
    }
    return -1;
}

int WizardPotionInventorySystem::findPotionIndex(int wizardIdx, const string &potionName) const
{
    for (int i = 0; i < wizardList[wizardIdx].potionCount; i++)
    {

        if (wizardList[wizardIdx].potions[i].potionName == potionName)
        {

            return i;
        }
    }
    return -1;
}
void WizardPotionInventorySystem::merge(const StudentWizard **arr, int left, int mid, int right) const
{
    int n1 = mid - left + 1;
    int n2 = right - mid;

    const StudentWizard **leftSubArray = new const StudentWizard *[n1];
    const StudentWizard **rightSubArray = new const StudentWizard *[n2];

    for (int i = 0; i < n1; i++)
        leftSubArray[i] = arr[left + i];
    for (int j = 0; j < n2; j++)
        rightSubArray[j] = arr[mid + 1 + j];

    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2)
    {
        if (leftSubArray[i]->name <= rightSubArray[j]->name)
        {
            arr[k] = leftSubArray[i];
            i++;
        }
        else
        {
            arr[k] = rightSubArray[j];
            j++;
        }
        k++;
    }
    while (i < n1)
    {
        arr[k] = leftSubArray[i];
        i++;
        k++;
    }
    while (j < n2)
    {
        arr[k] = rightSubArray[j];
        j++;
        k++;
    }
    delete[] leftSubArray;
    delete[] rightSubArray;
}

void WizardPotionInventorySystem::mergeSort(const StudentWizard **arr, int left, int right) const
{
    if (left < right)
    {
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
}

void WizardPotionInventorySystem::showAllStudentWizards() const
{
    cout << "Student wizards in the system:" << endl;

    if (wizardCount == 0)
    {

        cout << "None" << endl;
        return;
    }
    const StudentWizard **temporaryWzrd = new const StudentWizard *[wizardCount];
    for (int i = 0; i < wizardCount; i++)
    {
        temporaryWzrd[i] = &wizardList[i];
    }

    if (wizardCount > 0)
    {
        mergeSort(temporaryWzrd, 0, wizardCount - 1);
    }

    for (int i = 0; i < wizardCount; i++)
    {
        int totalStrength = 0;
        for (int p = 0; p < temporaryWzrd[i]->potionCount; p++)
        {
            totalStrength += temporaryWzrd[i]->potions[p].strength;
        }

        cout << temporaryWzrd[i]->name << ", House: " << temporaryWzrd[i]->house << ", "
             << temporaryWzrd[i]->potionCount << " potion(s), " << totalStrength << " total strength." << endl;
    }

    delete[] temporaryWzrd;
}
void WizardPotionInventorySystem::brewPotion(const string studentName, const string potionName, const int strength)
{
    int wizardIndex = findWizardIndex(studentName);

    if (wizardIndex == -1)
    {

        cout << "Cannot brew potion. Student wizard " << studentName << " does not exist." << endl;
        return;
    }

    if (findPotionIndex(wizardIndex, potionName) != -1)
    {
        cout << "Cannot brew potion. Potion already exists in potion inventory of " << studentName << "." << endl;
        return;
    }

    int pCount = wizardList[wizardIndex].potionCount;
    Potion *potionArrayWillBeDeleted = new Potion[pCount + 1];

    for (int i = 0; i < pCount; i++)
    {

        potionArrayWillBeDeleted[i] = wizardList[wizardIndex].potions[i];
    }

    potionArrayWillBeDeleted[pCount].potionName = potionName;
    potionArrayWillBeDeleted[pCount].strength = strength;

    if (wizardList[wizardIndex].potions != nullptr)
    {
        delete[] wizardList[wizardIndex].potions;
    }

    wizardList[wizardIndex].potions = potionArrayWillBeDeleted;
    wizardList[wizardIndex].potionCount++;

    cout << "Brewed potion " << potionName << " for student wizard " << studentName << "." << endl;
}

void WizardPotionInventorySystem::addStudentWizard(const string name, const string house)
{

    if (findWizardIndex(name) != -1)
    {

        cout << "Cannot add student wizard. Student wizard " << name << " already exists." << endl;
        return;
    }

    StudentWizard *willBeDeletedArray = new StudentWizard[wizardCount + 1];

    for (int i = 0; i < wizardCount; i++)
    {
        willBeDeletedArray[i] = wizardList[i];
    }

    willBeDeletedArray[wizardCount].name = name;
    willBeDeletedArray[wizardCount].house = house;
    willBeDeletedArray[wizardCount].potions = nullptr;
    willBeDeletedArray[wizardCount].potionCount = 0;

    if (wizardList != nullptr)
    {
        delete[] wizardList;
    }

    wizardList = willBeDeletedArray;
    wizardCount++;
    cout << "Added student wizard " << name << "." << endl;
}

void WizardPotionInventorySystem::removeStudentWizard(const string name)
{

    int wizardIndxNumber = findWizardIndex(name);

    if (wizardIndxNumber == -1)
    {

        cout << "Cannot remove student wizard. Student wizard " << name << " does not exist." << endl;
        return;
    }

    if (wizardList[wizardIndxNumber].potions != nullptr)
    {
        delete[] wizardList[wizardIndxNumber].potions;
    }

    if (wizardCount == 1)
    {

        delete[] wizardList;
        wizardList = nullptr;
    }
    else
    {
        StudentWizard *willBeDeletedWizzardArray = new StudentWizard[wizardCount - 1];

        for (int i = 0; i < wizardIndxNumber; i++)
        {
            willBeDeletedWizzardArray[i] = wizardList[i];
        }

        for (int i = wizardIndxNumber + 1; i < wizardCount; i++)
        {
            willBeDeletedWizzardArray[i - 1] = wizardList[i];
        }

        delete[] wizardList;
        wizardList = willBeDeletedWizzardArray;
    }

    wizardCount--;
    cout << "Removed student wizard " << name << "." << endl;
}

void WizardPotionInventorySystem::discardPotion(const string studentName, const string potionName)
{
    int wizardIndxNumberPlace = findWizardIndex(studentName);

    if (wizardIndxNumberPlace == -1)
    {

        cout << "Cannot discard potion. Student wizard " << studentName << " does not exist." << endl;
        return;
    }

    int potionIndexNumber = findPotionIndex(wizardIndxNumberPlace, potionName);

    if (potionIndexNumber == -1)
    {
        cout << "Cannot discard potion. Potion does not exist in potion inventory of " << studentName << "." << endl;
        return;
    }
    if (wizardList[wizardIndxNumberPlace].potionCount == 1)
    {
        delete[] wizardList[wizardIndxNumberPlace].potions;
        wizardList[wizardIndxNumberPlace].potions = nullptr;
    }
    else
    {

        Potion *tempArray = new Potion[wizardList[wizardIndxNumberPlace].potionCount - 1];

        for (int i = 0; i < potionIndexNumber; i++)
        {
            tempArray[i] = wizardList[wizardIndxNumberPlace].potions[i];
        }

        for (int i = potionIndexNumber + 1; i < wizardList[wizardIndxNumberPlace].potionCount; i++)
        {
            tempArray[i - 1] = wizardList[wizardIndxNumberPlace].potions[i];
        }
        delete[] wizardList[wizardIndxNumberPlace].potions;
        wizardList[wizardIndxNumberPlace].potions = tempArray;
    }
    wizardList[wizardIndxNumberPlace].potionCount--;
    cout << "Discarded potion " << potionName << " from student wizard " << studentName << "." << endl;
}

void WizardPotionInventorySystem::transferPotion(const string potionName, const string fromStudent, const string toStudent)
{
    int senderWizardIndex = findWizardIndex(fromStudent);
    int receiverWizardIndex = findWizardIndex(toStudent);

    if (senderWizardIndex == -1 || receiverWizardIndex == -1)
    {
        cout << "Cannot transfer potion. One or both student wizards do not exist." << endl;
        return;
    }

    int potionIndex = findPotionIndex(senderWizardIndex, potionName);

    if (potionIndex == -1)
    {
        cout << "Cannot transfer potion. Potion does not exist in potion inventory of " << fromStudent << "." << endl;
        return;
    }

    if (findPotionIndex(receiverWizardIndex, potionName) != -1)
    {
        cout << "Cannot transfer potion. Potion already exists in potion inventory of " << toStudent << "." << endl;
        return;
    }

    int fromPotionCount = wizardList[senderWizardIndex].potionCount;
    int toPotionCount = wizardList[receiverWizardIndex].potionCount;

    Potion transferredPotion = wizardList[senderWizardIndex].potions[potionIndex];

    Potion *tempToPotions = new Potion[toPotionCount + 1];

    for (int i = 0; i < toPotionCount; i++)
    {
        tempToPotions[i] = wizardList[receiverWizardIndex].potions[i];
    }
    tempToPotions[toPotionCount] = transferredPotion;

    if (wizardList[receiverWizardIndex].potions != nullptr)
    {
        delete[] wizardList[receiverWizardIndex].potions;
    }
    wizardList[receiverWizardIndex].potions = tempToPotions;
    wizardList[receiverWizardIndex].potionCount++;

    if (fromPotionCount == 1)
    {
        delete[] wizardList[senderWizardIndex].potions;
        wizardList[senderWizardIndex].potions = nullptr;
    }
    else
    {
        Potion *tempFromPotions = new Potion[fromPotionCount - 1];

        for (int i = 0; i < potionIndex; i++)
        {
            tempFromPotions[i] = wizardList[senderWizardIndex].potions[i];
        }
        for (int i = potionIndex + 1; i < fromPotionCount; i++)
        {
            tempFromPotions[i - 1] = wizardList[senderWizardIndex].potions[i];
        }
        delete[] wizardList[senderWizardIndex].potions;
        wizardList[senderWizardIndex].potions = tempFromPotions;
    }
    wizardList[senderWizardIndex].potionCount--;

    cout << "Transferred potion " << potionName << " from " << fromStudent << " to " << toStudent << "." << endl;
}

void WizardPotionInventorySystem::showPotion(const string potionName) const
{
    int potionOwnerNumber = 0;

    for (int i = 0; i < wizardCount; i++)
    {
        for (int p = 0; p < wizardList[i].potionCount; p++)
        {
            if (wizardList[i].potions[p].potionName == potionName)
            {
                potionOwnerNumber++;
                break;
            }
        }
    }

    if (potionOwnerNumber == 0)
    {
        cout << "Potion " << potionName << " does not exist." << endl;
        return;
    }

    const StudentWizard **temporaryWzrd = new const StudentWizard *[potionOwnerNumber];
    int index = 0;

    for (int i = 0; i < wizardCount; i++)
    {
        for (int p = 0; p < wizardList[i].potionCount; p++)
        {
            if (wizardList[i].potions[p].potionName == potionName)
            {
                temporaryWzrd[index] = &wizardList[i];
                index++;
                break;
            }
        }
    }

    if (potionOwnerNumber > 0)
    {
        mergeSort(temporaryWzrd, 0, potionOwnerNumber - 1);
    }

    cout << "Potion \"" << potionName << "\" found in " << potionOwnerNumber << " student wizard(s):" << endl;
    for (int i = 0; i < potionOwnerNumber; i++)
    {
        int str = 0;
        for (int p = 0; p < temporaryWzrd[i]->potionCount; p++)
        {
            if (temporaryWzrd[i]->potions[p].potionName == potionName)
            {
                str = temporaryWzrd[i]->potions[p].strength;
                break;
            }
        }
        cout << (i + 1) << ". " << temporaryWzrd[i]->name << ", strength " << str << "." << endl;
    }
    delete[] temporaryWzrd;
}

void WizardPotionInventorySystem::showStudentWizard(const string name) const
{
    int wzrdArrayIndex = findWizardIndex(name);

    if (wzrdArrayIndex == -1)
    {
        cout << "Student wizard " << name << " does not exist." << endl;
        return;
    }

    int totalStrength = 0;
    for (int pstIndex = 0; pstIndex < wizardList[wzrdArrayIndex].potionCount; pstIndex++)
    {
        totalStrength += wizardList[wzrdArrayIndex].potions[pstIndex].strength;
    }

    cout << "Student wizard:" << endl;
    cout << wizardList[wzrdArrayIndex].name << ", House: " << wizardList[wzrdArrayIndex].house << ", "
         << wizardList[wzrdArrayIndex].potionCount << " potion(s), " << totalStrength << " total strength." << endl;

    if (wizardList[wzrdArrayIndex].potionCount > 0)
    {
        cout << "Potions:" << endl;
        for (int pstIndex = 0; pstIndex < wizardList[wzrdArrayIndex].potionCount; pstIndex++)
        {
            cout << wizardList[wzrdArrayIndex].potions[pstIndex].potionName << ", strength "
                 << wizardList[wzrdArrayIndex].potions[pstIndex].strength << "." << endl;
        }
    }
}