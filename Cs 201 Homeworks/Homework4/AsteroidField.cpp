#include "AsteroidField.h"
#include <fstream>
// Name: Eralp Yigit Boz
// Section: 2
// Student Number: 22403188
// Cs 201 Homework 4
AsteroidStack::AsteroidStack() {
    topNode = nullptr;
}
AsteroidStack::~AsteroidStack() {
    while (!isEmpty()) {
        AsteroidNode* temp = pop();
        delete temp;
    }
}
void AsteroidStack::push(AsteroidNode* ast) {
    if (ast != nullptr) {
        (*ast).nxtAstr = topNode;
        topNode = ast;
    }
}
AsteroidNode* AsteroidStack::pop() {
    if (isEmpty()) {
        return nullptr;
    }
    AsteroidNode* astTmp = topNode;
    topNode = (*topNode).nxtAstr;
    (*astTmp).nxtAstr = nullptr; 
    return astTmp;
}
AsteroidNode* AsteroidStack::peek() const {
    return topNode;
}
bool AsteroidStack::isEmpty() const {
    return topNode == nullptr;
}
AsteroidField::AsteroidField(const string asteroidFile) {
    astHeadNode = nullptr; 
    astDestroyedHead = nullptr;
    ifstream file(asteroidFile.c_str());
    if (!file.is_open()) {
        return; 
    }
    int n;
    file >> n;
    for (int a = 0; a < n; ++a) {
        int astId, astEng;
        char astDrc;
        file >> astId >> astEng >> astDrc;
        AsteroidNode* newNode = new AsteroidNode(astId, astEng, astDrc);
        if (astHeadNode == nullptr) {
            astHeadNode = newNode;
        } else {
            AsteroidNode* astTmp = astHeadNode;
            while ((*astTmp).nxtAstr != nullptr) {
                astTmp = (*astTmp).nxtAstr;
            }
            (*astTmp).nxtAstr = newNode;
        }
    }
    file.close();
}
AsteroidField::~AsteroidField() {
    if (astHeadNode != nullptr) {
        AsteroidNode* astTmp = nullptr;
        while (astHeadNode != nullptr) {
            astTmp = (*astHeadNode).nxtAstr;
            delete astHeadNode;
            astHeadNode = astTmp;
        }
    }
    if (astDestroyedHead != nullptr) {
        AsteroidNode* astTmp = nullptr;
        while (astDestroyedHead != nullptr) {
            astTmp = (*astDestroyedHead).nxtAstr;
            delete astDestroyedHead;
            astDestroyedHead = astTmp;
        }
    }
}
void AsteroidField::display() const {
    if (astHeadNode == nullptr) {
        cout << "Asteroid field is empty." << endl;
        return;
    }
    cout << "Current asteroid field:" << endl;
    AsteroidNode* astTmp = astHeadNode;
    while (astTmp != nullptr) {
        cout << "A" << (*astTmp).astrdIdNmbr << "(" << (*astTmp).astdEnrgyNmbr << "," << (*astTmp).astDir << ")";
        if ((*astTmp).nxtAstr != nullptr) {
            cout << " ";
        }
        astTmp = (*astTmp).nxtAstr;
    }
    cout << endl; 
}
void AsteroidField::collide() {
    AsteroidStack* sck = new AsteroidStack();
    AsteroidNode* curr = astHeadNode;
    astHeadNode = nullptr;
    while (curr != nullptr) {
        AsteroidNode* nextBackup = (*curr).nxtAstr;
        (*curr).nxtAstr = nullptr;

        if ((*curr).astDir == 'R') {
            (*sck).push(curr);
        } 
        else if ((*curr).astDir == 'L') {
            bool crrDst = false;
            while (!(*sck).isEmpty() && (*(*sck).peek()).astDir == 'R') {
                AsteroidNode* rightNode = (*sck).peek();
                if ((*rightNode).astdEnrgyNmbr == (*curr).astdEnrgyNmbr) {
                    AsteroidNode* deadR = (*sck).pop();
                    (*deadR).nxtAstr = astDestroyedHead;
                    astDestroyedHead = deadR;
                    (*curr).nxtAstr = astDestroyedHead;
                    astDestroyedHead = curr;
                    crrDst = true;
                    break; 
                } 
                else if ((*rightNode).astdEnrgyNmbr > (*curr).astdEnrgyNmbr) {
                    (*rightNode).astdEnrgyNmbr -= (*curr).astdEnrgyNmbr; 
                    (*curr).nxtAstr = astDestroyedHead;
                    astDestroyedHead = curr;
                    crrDst = true;
                    break;
                } 
                else if ((*rightNode).astdEnrgyNmbr < (*curr).astdEnrgyNmbr) {
                    (*curr).astdEnrgyNmbr -= (*rightNode).astdEnrgyNmbr; 
                    AsteroidNode* deadR = (*sck).pop();
                    (*deadR).nxtAstr = astDestroyedHead;
                    astDestroyedHead = deadR;
                }
            }
            if (crrDst == false) {
                (*sck).push(curr);
            }
        }
        curr = nextBackup; 
    }
    while (!(*sck).isEmpty()) {
        AsteroidNode* survivor = (*sck).pop();
        (*survivor).nxtAstr = astHeadNode;
        astHeadNode = survivor;
    }
    delete sck;
    cout << "Collision simulation complete." << endl;
}
void AsteroidField::addAsteroid(const int id, const int energy, const char direction) {
    AsteroidNode* newAsteroid = new AsteroidNode(id, energy, direction);
    if (astHeadNode == nullptr) {
        astHeadNode = newAsteroid;
        cout << "Asteroid A" << id << " added." << endl; 
        return;
    }
    AsteroidNode* crnt = astHeadNode;
    while ((*crnt).nxtAstr != nullptr) {
        crnt = (*crnt).nxtAstr;
    }
    (*crnt).nxtAstr = newAsteroid;
    cout << "Asteroid A" << id << " added." << endl;
}
void AsteroidField::reverseLastAsteroid() {
    if (astHeadNode == nullptr) {
        cout << "No asteroid to reverse." << endl;
        return;
    }
    AsteroidNode* crnt = astHeadNode;
    while ((*crnt).nxtAstr != nullptr) {
        crnt = (*crnt).nxtAstr;
    }
    if ((*crnt).astDir == 'R') {
        (*crnt).astDir = 'L';
    } else if ((*crnt).astDir == 'L') {
        (*crnt).astDir = 'R';
    }
    cout << "Asteroid A" << (*crnt).astrdIdNmbr << " reversed." << endl;
}
void AsteroidField::reverseField() {
    if (astHeadNode == nullptr) {
        cout << "Asteroid field is empty." << endl;
        return;
    }
    AsteroidNode* prevNode = nullptr;
    AsteroidNode* crnt = astHeadNode;
    AsteroidNode* nextNode = nullptr;
    while (crnt != nullptr) {
        nextNode = (*crnt).nxtAstr;
        (*crnt).nxtAstr = prevNode;
        prevNode = crnt;
        crnt = nextNode;
    }
    astHeadNode = prevNode;
    cout << "Asteroid field order reversed." << endl;
}
void AsteroidField::displayAsteroidStatus(const int id) const {
    AsteroidNode* crnt = astHeadNode;
    while (crnt != nullptr) {
        if ((*crnt).astrdIdNmbr == id) {
            cout << "Asteroid A" << id << " is active with energy " 
                 << (*crnt).astdEnrgyNmbr << " and direction " << (*crnt).astDir << "." << endl;
            return; 
        }
        crnt = (*crnt).nxtAstr;
    }
    crnt = astDestroyedHead;
    while (crnt != nullptr) {
        if ((*crnt).astrdIdNmbr == id) {
            cout << "Asteroid A" << id << " is destroyed." << endl;
            return;
        }
        crnt = (*crnt).nxtAstr;
    }
    cout << "Asteroid A" << id << " does not exist." << endl;
}