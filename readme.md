# Tchotchke
Collection of things not in the JDK.

# Brief summary of changes per release

## Java 8
* Lambda expression support in APIs
* Stream API
* Functional interface and default methods
* Optionals
* Nashorn – JavaScript runtime which allows developers to embed JavaScript code within applications
* Annotation on Java Types
* Unsigned Integer Arithmetic
* Repeating annotations
* New Date and Time API
* Statically-linked JNI libraries
* Launch JavaFX applications from jar files
* Remove the permanent generation from GC

## Java 9
* Java platform module system
* Interface Private Methods
* HTTP 2 Client
* JShell – REPL Tool
* Platform and JVM Logging
* Process API Updates
* Collection API Updates
* Improvements in Stream API
* Multi-Release JAR Files
* @Deprecated Tag Changes
* Stack Walking
* Java Docs Updates
* Miscellaneous Other Features

## Java 10
* JEP 286: Local Variable Type Inference
* JEP 322: Time-Based Release Versioning
* JEP 304: Garbage-Collector Interface
* JEP 307: Parallel Full GC for G1
* JEP 316: Heap Allocation on Alternative Memory Devices
* JEP 296: Consolidate the JDK Forest into a Single Repository
* JEP 310: Application Class-Data Sharing
* JEP 314: Additional Unicode Language-Tag Extensions
* JEP 319: Root Certificates
* JEP 317: Experimental Java-Based JIT Compiler
* JEP 312: Thread-Local Handshakes
* JEP 313: Remove the Native-Header Generation Tool
* New Added APIs and Options
* Removed APIs and Options

## Java 11
* HTTP Client API
* Launch Single-File Programs Without Compilation
* String API Changes
* Collection.toArray(IntFunction)
* Files.readString() and Files.writeString()
* Optional.isEmpty()

## Java 12
* Collectors.teeing() in Stream API
* String API Changes
* Files.mismatch(Path, Path)
* Compact Number Formatting
* Support for Unicode 11
* Switch Expressions (Preview)

## Java 13
* JEP 355 – Text Blocks (Preview)
* JEP 354 – Switch Expressions Enhancements (Preview)
* JEP 353 – Reimplement the Legacy Socket API
* JEP 350 – Dynamic CDS Archive
* JEP 351 – ZGC: Uncommit Unused Memory
* FileSystems.newFileSystem() Method
* DOM and SAX Factories with Namespace Support

## Java 14
* JEP 305 – Pattern Matching for instanceof (Preview)
* JEP 368 – Text Blocks (Second Preview)
* JEP 358 – Helpful NullPointerExceptions
* JEP 359 – Records (Preview)
* JEP 361 – Switch Expressions (Standard)
* JEP 343 – Packaging Tool (Incubator)
* JEP 345 – NUMA-Aware Memory Allocation for G1
* JEP 349 – JFR Event Streaming
* JEP 352 – Non-Volatile Mapped Byte Buffers
* JEP 363 – Remove the Concurrent Mark Sweep (CMS) Garbage Collector
* JEP 367 – Remove the Pack200 Tools and API
* JEP 370 – Foreign-Memory Access API (Incubator)

## Java 15
* Sealed Classes and Interfaces (Preview) (JEP 360)
* EdDSA Algorithm (JEP 339)
* Hidden Classes (JEP 371)
* Pattern Matching for instanceof (Second Preview) (JEP 375)
* Removed Nashorn JavaScript Engine (JEP 372)
* Reimplement the Legacy DatagramSocket API (JEP 373)
* Records (Second Preview) (JEP 384)
* Text Blocks become a standard feature. (JEP 378)

## Java 16
* JEP 338: Vector API (Incubator)
* JEP 347: Enable C++14 Language Features
* JEP 357: Migrate from Mercurial to Git
* JEP 369: Migrate to GitHub
* JEP 376: ZGC: Concurrent Thread-Stack Processing
* JEP 380: Unix-Domain Socket Channels
* JEP 386: Alpine Linux Port
* JEP 387: Elastic Metaspace
* JEP 388: Windows/AArch64 Port
* JEP 389: Foreign Linker API (Incubator)
* JEP 390: Warnings for Value-Based Classes
* JEP 392: Packaging Tool
* JEP 393: Foreign-Memory Access API (Third Incubator)
* JEP 394: Pattern Matching for instanceof
* JEP 395: Records
* JEP 396: Strongly Encapsulate JDK Internals by Default
* JEP 397: Sealed Classes (Second Preview)

## Java 17
* (JEP-306) Restore Always-Strict Floating-Point Semantics
* (JEP-356) Enhanced Pseudo-Random Number Generators
* (JEP-382) New macOS Rendering Pipeline
* (JEP-391) macOS/AArch64 Port
* (JEP-398) Deprecate the Applet API for Removal
* (JEP-403) Strongly Encapsulate JDK Internals
* (JEP-406) Pattern Matching for switch (Preview)
* (JEP-407) Remove RMI Activation
* (JEP-409) Sealed Classes
* (JEP-410) Remove the Experimental AOT and JIT Compiler
* (JEP-411) Deprecate the Security Manager for Removal
* (JEP-412) Foreign Function & Memory API (Incubator)
* (JEP-414) Vector API (Second Incubator)
* (JEP-415) Context-Specific Deserialization Filters

## Java 18
* JEP-400: UTF-8 by Default
* JEP-408: Simple Web Server
* JEP-413: Code Snippets in Java API Documentation
* JEP-416: Reimplement Core Reflection with Method Handles
* JEP-417: Vector API (Third Incubator)
* JEP-418: Internet-Address Resolution SPI
* JEP-419: Foreign Function & Memory API (Second Incubator)
* JEP-420: Pattern Matching for switch (Second Preview)
* JEP-421: Deprecate Finalization for Removal

## Java 19
* Record Patterns (Preview) [JEP-405]
* Linux/RISC-V Port [JEP-422]
* Foreign Function & Memory API (Preview) [JEP-424]
* Virtual Threads (Preview) [JEP-425]
* Vector API (Fourth Incubator) [JEP-426]
* Pattern Matching for switch (Third Preview) [JEP-427]
* Structured Concurrency (Incubator) [JEP-428]

## Java 20
* Scoped Values (Incubator) [JEP – 429]
* Record Patterns (Second Preview) [JEP – 432]
* Pattern Matching for switch (Fourth Preview) [JEP – 433]
* Foreign Function & Memory API (Second Preview) [JEP – 434]
* Virtual Threads (Second Preview) [JEP – 436]
* Structured Concurrency (Second Incubator) [JEP – 437]
* Vector API (Fifth Incubator) [JEP – 438]

## Java 21
* String Templates (Preview) [JEP-430]
* Sequenced Collections [JEP-431]
* Generational ZGC [JEP-439]
* Record Patterns [JEP-440]
* Pattern Matching for switch [JEP-441]
* Foreign Function & Memory API (Third Preview) [JEP-442]
* Unnamed Patterns and Variables (Preview) [JEP-443]
* Virtual Threads [JEP-444]
* Unnamed Classes and Instance Main Methods (Preview) [JEP-445]
* Scoped Values (Preview) [JEP-446]
* Vector API (Sixth Incubator) [JEP-448]
* Deprecate the Windows 32-bit x86 Port for Removal [JEP-449]
* Prepare to Disallow the Dynamic Loading of Agents [JEP-451]
* Key Encapsulation Mechanism API [JEP-452]
* Structured Concurrency (Preview) [JEP-453]

## Java 22
* Region Pinning for G1 [JEP 423]
* Statements before super(…) (Preview) [JEP 447]
* Foreign Function & Memory API [JEP 454]
* Unnamed Variables & Patterns [JEP 456]
* Class-File API (Preview) [JEP 457]
* Launch Multi-File Source-Code Programs [JEP 458]
* String Templates (Second Preview) [JEP 459]
* Vector API (Seventh Incubator) [JEP 460]
* Stream Gatherers (Preview) [JEP 461]
* Structured Concurrency (Second Preview) [JEP 462]
* Implicitly Declared Classes and Instance Main Methods (Second Preview) [JEP 463]
* Scoped Values (Second Preview) [JEP 464]

## Java 23
* Primitive Type Patterns in switch (Preview - JEP 455)
* Class-File API (Preview - JEP 457)
* Structured Concurrency (Third Preview)

## Java 24
* Finalized Feature: Foreign Function & Memory API (Project Panama)
* A Standard API for Bytecode: The Class-File API

## Java 25
* Virtual Threads (from Loom)
* Structured Concurrency (from Loom)
* Foreign Function & Memory API (from Panama)
* Full Pattern Matching and Record Patterns (from Amber)

eof
