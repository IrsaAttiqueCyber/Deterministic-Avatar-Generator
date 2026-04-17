# Deterministic-Avatar-Generator
Project Overview
The Avatar Generator is a Java-based application that creates unique, deterministic character profiles based on user input. By utilizing cryptographic hashing (SHA-256), the system ensures that the same set of input data (Name, Age, Gender, Country, and City) always produces the exact same avatar appearance and archetype.
This project demonstrates the practical application of hashing algorithms, data normalization, and object-oriented programming in Java.
Core Features
Deterministic Generation: Uses a seed-based approach where your digital identity is tied directly to your personal data.
Cryptographic Hash Engine: Implements the SHA-256 algorithm to generate a robust 32-bit integer seed from a normalized string of user details.
Appearance Customization: Randomly (but consistently) selects traits including:
Hair Color (Black, Brown, Blonde, etc.)
Eye Color (Brown, Blue, Green, etc.)
Face Shape (Oval, Round, Square, etc.)
Height and Clothing Styles.
Archetype Assignment: Categorizes users into specific personas such as Strategist, Artist, Analyst, Explorer, or Guardian.
Data Normalization: Automatically handles case sensitivity and whitespace to ensure consistency (e.g., "Islamabad" and " islamabad " produce the same result).
Technical Components
1. Hash Engine
The engine takes user attributes and merges them into a "canonical string." It then digests this string using MessageDigest to create a byte array, which is converted into a positive integer. This integer serves as the "DNA" for the avatar.
2. Appearance Generator
This module contains predefined arrays of physical traits. It uses the modulo operator (seed % traits.length) to pick specific attributes from the lists, ensuring the selection is both balanced and repeatable.
3. Archetype Generator
Similar to the appearance module, this uses the hash value to map the user to a specific personality archetype, providing a "role" for the generated character.
How to Run
Environment: Ensure you have Java Development Kit (JDK) installed.
Compilation:
Bash
javac AvatarGenerator.java
Execution:
Bash
java AvatarGenerator
Example Output
Plaintext
Deterministic Hash: 1472839402
Appearance [Hair: Black, Eyes: Brown, Face: Oval, Height: 175cm, Clothing: Casual]
Archetype: Strategist
Developer: Irsa Attique
Technologies: Java, SHA-256, OOP Principles
