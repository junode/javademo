<<<<<<< HEAD
class XmlExample {
  static def PERSON =
  """
  <person id='2'>
    <name>Gweneth</name>
    <age>1</age>
  </person>
  """
}

class Person {def id; def name; def age}

def xmlPerson = new XmlParser().parseText(XmlExample.PERSON)

Person p = new Person(id: xmlPerson.@id, name: xmlPerson.name.text(), 
                      age: xmlPerson.age.text())

=======
class XmlExample {
  static def PERSON =
  """
  <person id='2'>
    <name>Gweneth</name>
    <age>1</age>
  </person>
  """
}

class Person {def id; def name; def age}

def xmlPerson = new XmlParser().parseText(XmlExample.PERSON)

Person p = new Person(id: xmlPerson.@id, name: xmlPerson.name.text(), 
                      age: xmlPerson.age.text())

>>>>>>> ce45c9b3713495949ba406e619e7db16886d0e69
println "${p.id}, ${p.name}, ${p.age}"