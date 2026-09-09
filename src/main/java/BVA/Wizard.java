package BVA;

public class Wizard {
    final int MAX_MANA_STORE = 600;
    final int MAX_LEVEL = 50;
    final int MIN_LEVEL = 1;
    final int MIN_MANA_INVESTED = 1;
    final int SAFE_CAST_MOD = 10;
    final int OVERCHARGE_MOD = 12;
    final double OVERCHARGE_BONUS = 1.5;
    private int HP;
    private int manaStore = MAX_MANA_STORE;

    // Okay, so heroLevel would probably be an internal variable, but it is easier
    // to write unit tests if we send it in as a parameter.  We'll do OOP testing
    // later.
    public int spellSurge(int heroLevel, int manaInvested) {
        if (heroLevel < MIN_LEVEL)
            throw new IllegalArgumentException("Hero level must be at least 1");
        if (manaInvested < MIN_MANA_INVESTED)
            throw new IllegalArgumentException("Mana invested must be positive");
        if (heroLevel > MAX_LEVEL)
            throw new IllegalArgumentException("Hero level must be less than 50");
        if (manaInvested > manaStore)
            throw new IllegalArgumentException("Mana invested must be less than or equal to mana store");
        if (manaInvested <= SAFE_CAST_MOD * heroLevel)
            return manaInvested;
        if (manaInvested <= OVERCHARGE_MOD * heroLevel)
            return (int) (manaInvested * OVERCHARGE_BONUS);
        return 0;






    }




}
