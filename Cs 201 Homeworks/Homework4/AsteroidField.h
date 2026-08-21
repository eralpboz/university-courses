#ifndef ASTEROIDFIELD_H
#define ASTEROIDFIELD_H

#include <string>
#include <iostream>

// Name: Eralp Yigit Boz
// Section: 2
// Student Number: 22403188
// Cs 201 Homework 4

using namespace std;

struct AsteroidNode
{
    int astrdIdNmbr;
    int astdEnrgyNmbr;
    char astDir;
    AsteroidNode *nxtAstr;

    AsteroidNode(int i, int e, char d)
    {
        astrdIdNmbr = i;
        astdEnrgyNmbr = e;
        astDir = d;
        nxtAstr = nullptr;
    }
};

class AsteroidStack
{
private:
    AsteroidNode *topNode;

public:
    AsteroidStack();
    ~AsteroidStack();

    void push(AsteroidNode *ast);
    AsteroidNode *pop();
    AsteroidNode *peek() const;
    bool isEmpty() const;
};

class AsteroidField
{
public:
    AsteroidField(const string asteroidFile);
    ~AsteroidField();

    void display() const;
    void collide();
    void addAsteroid(const int id, const int energy, const char direction);
    void reverseLastAsteroid();
    void reverseField();
    void displayAsteroidStatus(const int id) const;

private:
    AsteroidNode *astDestroyedHead;
    AsteroidNode *astHeadNode;
};

#endif