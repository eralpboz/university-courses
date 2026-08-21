//Name: Eralp Yigit Boz
//Section: 2
//Student Number: 22403188
//Cs 201 Homework 3 

#ifndef HOSPITAL_SYSTEM_H
#define HOSPITAL_SYSTEM_H

#include <string>
using namespace std;

class HospitalSystem {
public:
    HospitalSystem();
    ~HospitalSystem();
    
    void addDoctor(const int doctorId, const string doctorName, const string specialty);
    void removeDoctor(const int doctorId);
    
    void addPatient(const int patientId, const string patientName);
    void removePatient(const int patientId);
    
    void assignDoctor(const int patientId, const int doctorId);
    void unassignDoctor(const int patientId, const int doctorId);
    
    void addDisease(const int patientId, const string diseaseName);
    void cureDisease(const int patientId, const string diseaseName);
    
    void showAllPatients() const;
    void showAllDoctors() const;
    void showDoctorPatients(const int doctorId) const;
    void showPatientHistory(const int patientId) const;
    void showCurrentDiseases(const int patientId) const;

private:
    
    struct SicknessNode {
        string sicknessName;
        SicknessNode* next;
        
        SicknessNode(const string& name) : sicknessName(name), next(nullptr) {}
    };

    struct AssignedIllPeopleNode {
        int illPeopleIdNmbr;
        AssignedIllPeopleNode* next;
        
        AssignedIllPeopleNode(int id) : illPeopleIdNmbr(id), next(nullptr) {}
    };

  
    struct DoctorNode {
        int doctorIdNmbr;
        string docName;
        string spec;
        AssignedIllPeopleNode* assignedIllPeopleHead; 
        DoctorNode* next;
        
        DoctorNode(int id, const string& name, const string& sp)
            : doctorIdNmbr(id), docName(name), spec(sp), 
              assignedIllPeopleHead(nullptr), next(nullptr) {}
    };

    
    struct IllPeopleNode {
        int illPeopleIdNmbr;
        string illPeopleName;
        int familyDocIdNmbr; 
        SicknessNode* currentSicknessesHead;  
        SicknessNode* previousSicknessesHead; 
        IllPeopleNode* next;
        
        IllPeopleNode(int id, const string& name)
        : illPeopleIdNmbr(id), illPeopleName(name), familyDocIdNmbr(-1), 
              currentSicknessesHead(nullptr), previousSicknessesHead(nullptr), next(nullptr) {}
    };

  
    DoctorNode* docsHead;
    IllPeopleNode* illPeoplesHead;

    DoctorNode* findDoctor(int id) const;
    IllPeopleNode* findIllPeople(int id) const;
};

#endif