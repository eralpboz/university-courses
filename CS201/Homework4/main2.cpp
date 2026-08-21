#include <iostream>
#include "AsteroidField.h"

using namespace std;

int main() {
    AsteroidField af("test2.txt");
    af.display();

    af.collide();
    af.display();

    af.reverseLastAsteroid();
    af.display();

    af.addAsteroid(6, 5, 'R');
    af.display();

    af.collide();
    af.display();

    af.reverseField();
    af.display();

    af.collide();
    af.display();

    return 0;
}