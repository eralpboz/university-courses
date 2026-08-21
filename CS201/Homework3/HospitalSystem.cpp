// Name: Eralp Yigit Boz
// Section: 2
// Student Number: 22403188
// Cs 201 Homework 3

#include "HospitalSystem.h"
#include <iostream>

using namespace std;

HospitalSystem::HospitalSystem()
{
    docsHead = nullptr;
    illPeoplesHead = nullptr;
}

HospitalSystem::~HospitalSystem()
{
    DoctorNode *doctorList = docsHead;
    IllPeopleNode *patientList = illPeoplesHead;

    while (doctorList != nullptr)
    {
        AssignedIllPeopleNode *doctorPatients = (*doctorList).assignedIllPeopleHead;

        while (doctorPatients != nullptr)
        {
            AssignedIllPeopleNode *temp = doctorPatients;
            doctorPatients = (*doctorPatients).next;
            delete temp;
        }
        doctorList = (*doctorList).next;
    }
    doctorList = docsHead;
    while (doctorList != nullptr)
    {
        DoctorNode *temp = doctorList;
        doctorList = (*doctorList).next;
        delete temp;
    }
    patientList = illPeoplesHead;
    while (patientList != nullptr)
    {

        SicknessNode *crntSickness = (*patientList).currentSicknessesHead;
        while (crntSickness != nullptr)
        {
            SicknessNode *tempSickness = crntSickness;
            crntSickness = (*crntSickness).next;
            delete tempSickness;
        }

        SicknessNode *prevSickness = (*patientList).previousSicknessesHead;
        while (prevSickness != nullptr)
        {
            SicknessNode *tempSickness = prevSickness;
            prevSickness = (*prevSickness).next;
            delete tempSickness;
        }

        IllPeopleNode *tempPatient = patientList;
        patientList = (*patientList).next;
        delete tempPatient;
    }
}

HospitalSystem::DoctorNode *HospitalSystem::findDoctor(int id) const
{

    DoctorNode *crnt = docsHead;
    while (crnt != nullptr)
    {

        if (crnt->doctorIdNmbr == id)
        {
            return crnt;
        }
        crnt = crnt->next;
    }

    return nullptr;
}

HospitalSystem::IllPeopleNode *HospitalSystem::findIllPeople(int id) const
{
    IllPeopleNode *crnt = illPeoplesHead;

    while (crnt != nullptr)
    {
        if (crnt->illPeopleIdNmbr == id)
        {
            return crnt;
        }
        crnt = crnt->next;
    }

    return nullptr;
}

void HospitalSystem::addDoctor(const int doctorId, const string doctorName, const string specialty)
{
    DoctorNode *newDoctor = findDoctor(doctorId);
    if (newDoctor != nullptr)
    {
        cout << "Cannot add doctor. There is already a doctor with ID " << doctorId << "." << endl;
        return;
    }

    newDoctor = new DoctorNode(doctorId, doctorName, specialty);
    DoctorNode *crnt = docsHead;
    DoctorNode *prevDoctor = nullptr;

    if (crnt == nullptr || doctorId < (*crnt).doctorIdNmbr)
    {
        (*newDoctor).next = docsHead;
        docsHead = newDoctor;
        cout << "Added doctor " << doctorId << "." << endl;
        return;
    }

    while (crnt != nullptr)
    {
        int oldDoctorId = (*crnt).doctorIdNmbr;

        if (doctorId < oldDoctorId)
        {
            (*newDoctor).next = crnt;
            (*prevDoctor).next = newDoctor;

            cout << "Added doctor " << doctorId << "." << endl;
            return;
        }
        prevDoctor = crnt;
        crnt = (*crnt).next;
    }
    (*prevDoctor).next = newDoctor;
    cout << "Added doctor " << doctorId << "." << endl;
}

void HospitalSystem::removeDoctor(const int doctorId)
{
    DoctorNode *doctorToBeDeleted = findDoctor(doctorId);
    if (doctorToBeDeleted == nullptr)
    {
        cout << "Cannot remove doctor. There is no doctor with ID " << doctorId << "." << endl;
        return;
    }
    if ((*doctorToBeDeleted).assignedIllPeopleHead != nullptr)
    {
        cout << "Cannot remove doctor. Doctor " << doctorId << " currently has assigned patients." << endl;
        return;
    }

    DoctorNode *crnt = docsHead;
    DoctorNode *prevDoctor = nullptr;

    if ((*crnt).doctorIdNmbr == doctorId)
    {
        docsHead = (*crnt).next;
        delete doctorToBeDeleted;
        cout << "Removed doctor " << doctorId << "." << endl;
        return;
    }

    while (crnt != nullptr)
    {

        if (doctorId == (*crnt).doctorIdNmbr)
        {

            (*prevDoctor).next = (*crnt).next;

            cout << "Removed doctor " << doctorId << "." << endl;
            delete doctorToBeDeleted;
            return;
        }
        prevDoctor = crnt;
        crnt = (*crnt).next;
    }
}

void HospitalSystem::addPatient(const int patientId, const string patientName)
{
    IllPeopleNode *newPatient = findIllPeople(patientId);
    if (newPatient != nullptr)
    {
        cout << "Cannot register patient. There is already a patient with ID " << patientId << "." << endl;
        return;
    }

    newPatient = new IllPeopleNode(patientId, patientName);
    IllPeopleNode *crnt = illPeoplesHead;
    IllPeopleNode *prevPatient = nullptr;

    if (crnt == nullptr || patientId < (*crnt).illPeopleIdNmbr)
    {
        (*newPatient).next = illPeoplesHead;
        illPeoplesHead = newPatient;
        cout << "Registered patient " << patientId << "." << endl;
        return;
    }

    while (crnt != nullptr)
    {
        int oldPatientId = (*crnt).illPeopleIdNmbr;

        if (patientId < oldPatientId)
        {
            (*newPatient).next = crnt;
            (*prevPatient).next = newPatient;

            cout << "Registered patient " << patientId << "." << endl;
            return;
        }
        prevPatient = crnt;
        crnt = (*crnt).next;
    }
    (*prevPatient).next = newPatient;
    cout << "Registered patient " << patientId << "." << endl;
}

void HospitalSystem::removePatient(const int patientId)
{
    IllPeopleNode *patientToBeDeleted = findIllPeople(patientId);
    if (patientToBeDeleted == nullptr)
    {
        cout << "Cannot unregister patient. There is no patient with ID " << patientId << "." << endl;
        return;
    }
    if ((*patientToBeDeleted).currentSicknessesHead != nullptr || (*patientToBeDeleted).familyDocIdNmbr != -1)
    {
        cout << "Cannot unregister patient. Patient " << patientId << " is still assigned to a doctor or has current diseases." << endl;
        return;
    }
    SicknessNode *crnt = (*patientToBeDeleted).previousSicknessesHead;
    SicknessNode *next;

    while (crnt != nullptr)
    {
        next = (*crnt).next;
        delete crnt;
        crnt = next;
    }

    IllPeopleNode *crntPatient = illPeoplesHead;
    IllPeopleNode *prevPatient = nullptr;

    if ((*crntPatient).illPeopleIdNmbr == patientId)
    {
        cout << "Unregistered patient " << patientId << "." << endl;
        illPeoplesHead = (*crntPatient).next;
        delete patientToBeDeleted;
        return;
    }

    while (crntPatient != nullptr)
    {
        if ((*crntPatient).illPeopleIdNmbr == patientId)
        {

            (*prevPatient).next = (*crntPatient).next;
            cout << "Unregistered patient " << patientId << "." << endl;
            delete patientToBeDeleted;
            return;
        }
        prevPatient = crntPatient;
        crntPatient = (*crntPatient).next;
    }
}

void HospitalSystem::assignDoctor(const int patientId, const int doctorId)
{
    DoctorNode *familyDoctor = findDoctor(doctorId);
    IllPeopleNode *unassignedPatient = findIllPeople(patientId);

    if (familyDoctor == nullptr)
    {
        cout << "Cannot assign doctor. There is no doctor with ID " << doctorId << "." << endl;
        return;
    }
    if (unassignedPatient == nullptr)
    {
        cout << "Cannot assign doctor. There is no patient with ID " << patientId << "." << endl;
        return;
    }

    if ((*unassignedPatient).familyDocIdNmbr != -1)
    {
        cout << "Cannot assign doctor. Patient " << patientId << " already has a family doctor." << endl;
        return;
    }

    (*unassignedPatient).familyDocIdNmbr = doctorId;

    AssignedIllPeopleNode *newPatient = new AssignedIllPeopleNode(patientId);

    AssignedIllPeopleNode *crnt = (*familyDoctor).assignedIllPeopleHead;
    AssignedIllPeopleNode *prev = nullptr;

    if (crnt == nullptr)
    {
        (*familyDoctor).assignedIllPeopleHead = newPatient;
        cout << "Patient " << patientId << " assigned to doctor " << doctorId << "." << endl;
        return;
    }
    while (crnt != nullptr)
    {
        prev = crnt;
        crnt = (*crnt).next;
    }
    (*prev).next = newPatient;
    cout << "Patient " << patientId << " assigned to doctor " << doctorId << "." << endl;
}

void HospitalSystem::unassignDoctor(const int patientId, const int doctorId)
{
    DoctorNode *familyDoctor = findDoctor(doctorId);
    IllPeopleNode *assignedPatient = findIllPeople(patientId);

    if (familyDoctor == nullptr)
    {
        cout << "Cannot unassign doctor. Doctor " << doctorId << " does not exist." << endl;
        return;
    }
    if (assignedPatient == nullptr)
    {
        cout << "Cannot unassign doctor. Patient " << patientId << " does not exist." << endl;
        return;
    }

    if ((*assignedPatient).familyDocIdNmbr != doctorId)
    {
        cout << "Cannot unassign doctor. Patient " << patientId << " does not have doctor " << doctorId << " as family doctor." << endl;
        return;
    }

    AssignedIllPeopleNode *crnt = (*familyDoctor).assignedIllPeopleHead;
    AssignedIllPeopleNode *prev = nullptr;

    if (crnt != nullptr && (*crnt).illPeopleIdNmbr == patientId)
    {
        (*familyDoctor).assignedIllPeopleHead = (*crnt).next;
        delete crnt;
        cout << "Patient " << patientId << " unassigned from doctor " << doctorId << "." << endl;
        (*assignedPatient).familyDocIdNmbr = -1;
        return;
    }

    while (crnt != nullptr)
    {
        if ((*crnt).illPeopleIdNmbr == patientId)
        {
            (*prev).next = (*crnt).next;
            delete crnt;
            cout << "Patient " << patientId << " unassigned from doctor " << doctorId << "." << endl;
            (*assignedPatient).familyDocIdNmbr = -1;
            return;
        }
        prev = crnt;
        crnt = (*crnt).next;
    }
}

void HospitalSystem::addDisease(const int patientId, const string diseaseName)
{
    IllPeopleNode *ptn = findIllPeople(patientId);
    if (ptn == nullptr)
    {
        cout << "Cannot add disease. There is no patient with ID " << patientId << "." << endl;
        return;
    }

    SicknessNode *crntSickness = (*ptn).currentSicknessesHead;
    while (crntSickness != nullptr)
    {
        if ((*crntSickness).sicknessName == diseaseName)
        {
            cout << "Cannot add disease. Patient " << patientId << " already has disease " << diseaseName << "." << endl;
            return;
        }
        crntSickness = (*crntSickness).next;
    }

    SicknessNode *prevSickness = (*ptn).previousSicknessesHead;
    while (prevSickness != nullptr)
    {
        if ((*prevSickness).sicknessName == diseaseName)
        {
            cout << "Cannot add disease. Patient " << patientId << " already has disease " << diseaseName << "." << endl;
            return;
        }
        prevSickness = (*prevSickness).next;
    }

    SicknessNode *newSickness = new SicknessNode(diseaseName);
    SicknessNode *crnt = (*ptn).currentSicknessesHead;
    SicknessNode *prev = nullptr;

    if (crnt == nullptr || diseaseName < (*crnt).sicknessName)
    {
        (*newSickness).next = (*ptn).currentSicknessesHead;
        (*ptn).currentSicknessesHead = newSickness;
        cout << "Added disease " << diseaseName << " to patient " << patientId << "." << endl;
        return;
    }

    while (crnt != nullptr)
    {
        if (diseaseName < (*crnt).sicknessName)
        {
            (*newSickness).next = crnt;
            (*prev).next = newSickness;
            cout << "Added disease " << diseaseName << " to patient " << patientId << "." << endl;
            return;
        }
        prev = crnt;
        crnt = (*crnt).next;
    }

    (*prev).next = newSickness;
    cout << "Added disease " << diseaseName << " to patient " << patientId << "." << endl;
}

void HospitalSystem::cureDisease(const int patientId, const string diseaseName)
{
    IllPeopleNode *ptn = findIllPeople(patientId);

    if (ptn == nullptr)
    {
        cout << "Cannot cure disease. There is no patient with ID " << patientId << "." << endl;
        return;
    }

    SicknessNode *crnt = (*ptn).currentSicknessesHead;
    SicknessNode *prev = nullptr;
    SicknessNode *curedSickness = nullptr;

    while (crnt != nullptr)
    {
        if ((*crnt).sicknessName == diseaseName)
        {
            curedSickness = crnt;

            if (prev == nullptr)
            {
                (*ptn).currentSicknessesHead = (*crnt).next;
            }
            else
            {
                (*prev).next = (*crnt).next;
            }
            break;
        }
        prev = crnt;
        crnt = (*crnt).next;
    }

    if (curedSickness == nullptr)
    {
        cout << "Cannot cure disease. Patient " << patientId << " does not have disease " << diseaseName << " in their current records." << endl;
        return;
    }

    SicknessNode *prevCrnt = (*ptn).previousSicknessesHead;
    SicknessNode *prevPrev = nullptr;

    (*curedSickness).next = nullptr;

    if (prevCrnt == nullptr || diseaseName < (*prevCrnt).sicknessName)
    {
        (*curedSickness).next = (*ptn).previousSicknessesHead;
        (*ptn).previousSicknessesHead = curedSickness;
        cout << "Cured disease " << diseaseName << " for patient " << patientId << "." << endl;
        return;
    }

    while (prevCrnt != nullptr)
    {
        if (diseaseName < (*prevCrnt).sicknessName)
        {
            (*curedSickness).next = prevCrnt;
            (*prevPrev).next = curedSickness;
            cout << "Cured disease " << diseaseName << " for patient " << patientId << "." << endl;
            return;
        }
        prevPrev = prevCrnt;
        prevCrnt = (*prevCrnt).next;
    }

    (*prevPrev).next = curedSickness;
    cout << "Cured disease " << diseaseName << " for patient " << patientId << "." << endl;
}

void HospitalSystem::showAllPatients() const
{
    if (illPeoplesHead == nullptr)
    {
        cout << "There are no patients to show." << endl;
        return;
    }

    cout << "Patients in the hospital:" << endl;

    IllPeopleNode *crnt = illPeoplesHead;
    while (crnt != nullptr)
    {

        cout << "Patient " << (*crnt).illPeopleIdNmbr << " : " << (*crnt).illPeopleName << endl;
        crnt = (*crnt).next;
    }
}

void HospitalSystem::showAllDoctors() const
{
    if (docsHead == nullptr)
    {
        cout << "There are no doctors to show." << endl;
        return;
    }

    cout << "Doctors in the hospital:" << endl;

    DoctorNode *crnt = docsHead;
    while (crnt != nullptr)
    {

        cout << "Doctor " << (*crnt).doctorIdNmbr << " : " << (*crnt).docName << " (" << (*crnt).spec << ")" << endl;
        crnt = (*crnt).next;
    }
}

void HospitalSystem::showDoctorPatients(const int doctorId) const
{
    DoctorNode *doc = findDoctor(doctorId);
    if (doc == nullptr)
    {
        cout << "Cannot show patients. There is no doctor with ID " << doctorId << "." << endl;
        return;
    }

    if ((*doc).assignedIllPeopleHead == nullptr)
    {
        cout << "Doctor " << doctorId << " has no assigned patients." << endl;
        return;
    }

    cout << "Patients of doctor " << doctorId << ":" << endl;
    AssignedIllPeopleNode *crntAssigned = (*doc).assignedIllPeopleHead;
    while (crntAssigned != nullptr)
    {
        IllPeopleNode *p = findIllPeople((*crntAssigned).illPeopleIdNmbr);

        cout << "Patient " << (*crntAssigned).illPeopleIdNmbr << " : " << (*p).illPeopleName << endl;
        crntAssigned = (*crntAssigned).next;
    }
}

void HospitalSystem::showCurrentDiseases(const int patientId) const
{
    IllPeopleNode *p = findIllPeople(patientId);
    if (p == nullptr)
    {
        cout << "Cannot show current diseases. There is no patient with ID " << patientId << "." << endl;
        return;
    }

    if ((*p).currentSicknessesHead == nullptr)
    {
        cout << "Patient " << patientId << " has no current diseases." << endl;
        return;
    }

    cout << "Current diseases for patient " << patientId << ": ";
    SicknessNode *crnt = (*p).currentSicknessesHead;
    while (crnt != nullptr)
    {
        cout << (*crnt).sicknessName;
        if ((*crnt).next != nullptr)
            cout << ", ";
        else
            cout << ".";
        crnt = (*crnt).next;
    }
    cout << endl;
}

void HospitalSystem::showPatientHistory(const int patientId) const
{
    IllPeopleNode *p = findIllPeople(patientId);
    if (p == nullptr)
    {
        cout << "Cannot show history. There is no patient with ID " << patientId << "." << endl;
        return;
    }

    // DİKKAT: "Patient" kelimesinden sonra boşluk var, iki noktadan önce YOK.
    cout << "Medical History for Patient " << (*p).illPeopleIdNmbr << ": " << (*p).illPeopleName << endl;

    cout << "Family Doctor: ";
    if ((*p).familyDocIdNmbr == -1)
        cout << "None." << endl;
    else
    {
        DoctorNode *d = findDoctor((*p).familyDocIdNmbr);
        cout << (*d).docName << "." << endl;
    }

    cout << "Current Diseases: ";
    if ((*p).currentSicknessesHead == nullptr)
        cout << "None." << endl;
    else
    {
        SicknessNode *crnt = (*p).currentSicknessesHead;
        while (crnt != nullptr)
        {
            cout << (*crnt).sicknessName;
            if ((*crnt).next != nullptr)
                cout << ", ";
            else
                cout << ".";
            crnt = (*crnt).next;
        }
        cout << endl;
    }

    cout << "Previous Diseases: ";
    if ((*p).previousSicknessesHead == nullptr)
        cout << "None." << endl;
    else
    {
        SicknessNode *crnt = (*p).previousSicknessesHead;
        while (crnt != nullptr)
        {
            cout << (*crnt).sicknessName;
            if ((*crnt).next != nullptr)
                cout << ", ";
            else
                cout << ".";
            crnt = (*crnt).next;
        }
        cout << endl;
    }
}