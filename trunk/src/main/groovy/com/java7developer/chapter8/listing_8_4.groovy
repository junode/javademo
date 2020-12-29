<<<<<<< HEAD
class Character
{
  private int strength
  private int wisdom
}
 
def pc = new Character(strength: 10, wisdom: 15)
pc.strength = 18
=======
class Character
{
  private int strength
  private int wisdom
}
 
def pc = new Character(strength: 10, wisdom: 15)
pc.strength = 18
>>>>>>> ce45c9b3713495949ba406e619e7db16886d0e69
println "STR [" + pc.strength + "], WIS [" + pc.wisdom + "]"