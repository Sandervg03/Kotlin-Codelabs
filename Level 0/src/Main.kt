import models.aquarium.Aquarium
import models.aquarium.TowerAquarium
import models.decoration.Decoration
import models.fish.Dolphin
import models.fish.Shark

fun main() {
//    buildAquarium();
//    catchFish();
//    makeDecorations();
}

fun buildAquarium() {
    try {
        val aquarium: Aquarium = Aquarium(height = 25, width = 40, length = 25);
        aquarium.showInformation();
        val towerAquarium: TowerAquarium = TowerAquarium(height = 40, diameter = 25);
        towerAquarium.showInformation();
    } catch (error: Error) {
        println("Error: ${error.message}")
    }
}

fun catchFish() {
    val shark: Shark = Shark();
    val dolphin: Dolphin = Dolphin();
    println("This shark is ${shark.color} and");
    shark.eat();
    println("---------------------")
    println("This dolphin is ${dolphin.color} and");
    dolphin.eat();
}

fun makeDecorations() {
    val decoration1: Decoration = Decoration("Granite");
    println(decoration1);
    val decoration2: Decoration = Decoration("Slate");
    println(decoration2);
    val decoration3: Decoration = Decoration("Slate");
    println(decoration3);
    println("--------------------");
    println(decoration1.equals(decoration2));
    println(decoration2.equals(decoration3));
    println("---------------------");
    val (rock, wood, diver) = Decoration("Granite", "Birch", "Spongebob Squarepants");
    println(rock);
    println(wood);
    println(diver);
}