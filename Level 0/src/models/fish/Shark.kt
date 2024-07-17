package models.fish

class Shark
    (override val name: String = "Shark") : Fish(), FishAction by PrintingFishAction(),
    FishColor by GrayFish