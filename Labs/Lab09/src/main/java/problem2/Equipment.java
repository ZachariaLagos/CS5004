package problem2;

/**
 * Interface for equipment that can modify character stats.
 */
public interface Equipment {
    int getAttackBonus();
    int getDefenseBonus();
    String getDescription();
}
