#include <iostream>
#include "AsteroidField.h"

using namespace std;

int main() {
    AsteroidField af("asteroidFile.txt");

    af.display();
    cout << endl;

    af.collide();
    af.display();
    cout << endl;

    af.displayAsteroidStatus(1);
    af.displayAsteroidStatus(2);
    af.displayAsteroidStatus(10);
    cout << endl;

    af.reverseLastAsteroid();
    af.display();
    cout << endl;

    af.collide();
    af.display();
    cout << endl;

    af.addAsteroid(7, 3, 'L');
    af.addAsteroid(8, 5, 'R');
    af.display();
    cout << endl;

    af.collide();
    af.display();
    cout << endl;

    af.reverseLastAsteroid();
    af.display();
    cout << endl;

    af.collide();
    af.display();
    cout << endl;

    af.displayAsteroidStatus(7);
    af.displayAsteroidStatus(8);
    cout << endl;

    af.addAsteroid(9, 4, 'R');
    af.addAsteroid(10, 2, 'L');
    af.display();
    cout << endl;

    af.collide();
    af.display();
    cout << endl;

    af.displayAsteroidStatus(9);
    af.displayAsteroidStatus(10);
    cout << endl;

    af.reverseField();
    af.display();
    cout << endl;

    af.collide();
    af.display();

    return 0;
}