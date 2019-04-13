package com.gsonkeno.jsontraining.model;

public class Person {
    String birthAddress;
    String name;
    String emailAddress;

    /**
     * 使用jackson返回对象到前端时，一定要有get方法存在，否则
     * No converter found for return value of type: class com.gsonkeno.jsontraining.model.Person
     **/
    public String getBirthAddress() {
        return birthAddress;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public static final class PersonBuilder {
        String birthAddress;
        String name;
        String emailAddress;

        public static PersonBuilder aPerson() {
            return new PersonBuilder();
        }

        public PersonBuilder withBirthAddress(String birthAddress) {
            this.birthAddress = birthAddress;
            return this;
        }

        public PersonBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Person build() {
            Person person = new Person();
            person.birthAddress = this.birthAddress;
            person.name = this.name;
            person.emailAddress = this.emailAddress;
            return person;
        }
    }
}
