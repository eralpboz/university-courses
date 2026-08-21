#include "WizardPotionInventorySystem.h"
#include <iostream>
/*
Name: Eralp Yigit Boz
Student Number: 22403188
Section: 2
Cs 201 Homework 2 (Optimized)
*/
using namespace std;

WizardPotionInventorySystem::WizardPotionInventorySystem()
{
    wizardCapacity = 2;
    wizardList = new StudentWizard[wizardCapacity];
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

void WizardPotionInventorySystem::merge(const StudentWizard **arr, const StudentWizard **workspace, int left, int mid, int right) const
{
    int i = left;
    int j = mid + 1;
    int k = left;

    while (i <= mid && j <= right)
    {
        if (arr[i]->name <= arr[j]->name)
        {
            workspace[k++] = arr[i++];
        }
        else
        {
            workspace[k++] = arr[j++];
        }
    }
    while (i <= mid)
    {
        workspace[k++] = arr[i++];
    }
    while (j <= right)
    {
        workspace[k++] = arr[j++];
    }
    for (int idx = left; idx <= right; idx++)
    {
        arr[idx] = workspace[idx];
    }
}

void WizardPotionInventorySystem::mergeSort(const StudentWizard **arr, const StudentWizard **workspace, int left, int right) const
{
    if (left < right)
    {
        int mid = left + (right - left) / 2;
        mergeSort(arr, workspace, left, mid);
        mergeSort(arr, workspace, mid + 1, right);
        merge(arr, workspace, left, mid, right);
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
    const StudentWizard **workspace = new const StudentWizard *[wizardCount];

    for (int i = 0; i < wizardCount; i++)
    {
        temporaryWzrd[i] = &wizardList[i];
    }

    if (wizardCount > 0)
    {
        mergeSort(temporaryWzrd, workspace, 0, wizardCount - 1);
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
    delete[] workspace;
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
    int pCap = wizardList[wizardIndex].potionCapacity;

    if (pCount == pCap)
    {
        wizardList[wizardIndex].potionCapacity *= 2;
        Potion *tempPotions = new Potion[wizardList[wizardIndex].potionCapacity];
        for (int i = 0; i < pCount; i++)
        {
            tempPotions[i] = wizardList[wizardIndex].potions[i];
        }
        if (wizardList[wizardIndex].potions != nullptr)
        {
            delete[] wizardList[wizardIndex].potions;
        }
        wizardList[wizardIndex].potions = tempPotions;
    }

    wizardList[wizardIndex].potions[pCount].potionName = potionName;
    wizardList[wizardIndex].potions[pCount].strength = strength;
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

    if (wizardCount == wizardCapacity)
    {
        wizardCapacity *= 2;
        StudentWizard *tempWizardList = new StudentWizard[wizardCapacity];
        for (int i = 0; i < wizardCount; i++)
        {
            tempWizardList[i] = wizardList[i];
            wizardList[i].potions = nullptr; 
        }
        delete[] wizardList;
        wizardList = tempWizardList;
    }

    wizardList[wizardCount].name = name;
    wizardList[wizardCount].house = house;
    wizardList[wizardCount].potionCount = 0;
    wizardList[wizardCount].potionCapacity = 2;
    wizardList[wizardCount].potions = new Potion[2];

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
        wizardList[wizardIndxNumber].potions = nullptr;
    }

    if (wizardIndxNumber != wizardCount - 1)
    {
        wizardList[wizardIndxNumber] = wizardList[wizardCount - 1];
        wizardList[wizardCount - 1].potions = nullptr;
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

    int pCount = wizardList[wizardIndxNumberPlace].potionCount;
    wizardList[wizardIndxNumberPlace].potions[potionIndexNumber] = wizardList[wizardIndxNumberPlace].potions[pCount - 1];
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

    Potion transferredPotion = wizardList[senderWizardIndex].potions[potionIndex];

    int toPotionCount = wizardList[receiverWizardIndex].potionCount;
    int toPotionCap = wizardList[receiverWizardIndex].potionCapacity;

    if (toPotionCount == toPotionCap)
    {
        wizardList[receiverWizardIndex].potionCapacity *= 2;
        Potion *tempToPotions = new Potion[wizardList[receiverWizardIndex].potionCapacity];
        for (int i = 0; i < toPotionCount; i++)
        {
            tempToPotions[i] = wizardList[receiverWizardIndex].potions[i];
        }
        if (wizardList[receiverWizardIndex].potions != nullptr)
        {
            delete[] wizardList[receiverWizardIndex].potions;
        }
        wizardList[receiverWizardIndex].potions = tempToPotions;
    }

    wizardList[receiverWizardIndex].potions[toPotionCount] = transferredPotion;
    wizardList[receiverWizardIndex].potionCount++;

    int fromPotionCount = wizardList[senderWizardIndex].potionCount;
    wizardList[senderWizardIndex].potions[potionIndex] = wizardList[senderWizardIndex].potions[fromPotionCount - 1];
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
    const StudentWizard **workspace = new const StudentWizard *[potionOwnerNumber];
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
        mergeSort(temporaryWzrd, workspace, 0, potionOwnerNumber - 1);
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
    delete[] workspace;
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