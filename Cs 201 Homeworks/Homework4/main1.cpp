#include <iostream>
#include "AsteroidField.h"

using namespace std;

int main() {
    AsteroidField af("test1.txt");
    af.display();

    af.collide();
    af.display();

    af.reverseLastAsteroid();
    af.reverseField();

    af.displayAsteroidStatus(1);
    af.displayAsteroidStatus(5);

    af.addAsteroid(5, 7, 'L');
    af.display();

    return 0;
}