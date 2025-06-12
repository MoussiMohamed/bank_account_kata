# Bank‑Account Kata


Minimal Java 17 / Maven kata – clean code, unit‑tested.

Run

mvn clean test   # build + tests

mvn -q exec:java # demo statement


# Quick start

# compile + tests (~1 s)
mvn clean test
# run CLI demo (prints a statement)
mvn -q exec:java

Expected console output:

DATE & TIME         | AMOUNT   | BALANCE

12/06/2025 14:xx:00 |  1000.00 | 1000.00

12/06/2025 14:xx:00 |  -200.00 |  800.00

12/06/2025 14:xx:00 |   500.00 | 1300.00

Project layout

└── src/main/java

    ├── domain         # records + core logic

    ├── ports          # two small interfaces

    ├── service        # AccountService (use‑case)

    └── infrastructure # console printer & in‑memory repo

└── src/test/java      # 4 focused unit tests

# Extending

Plug a real DB → implement TransactionRepository.

Expose REST → implement StatementPrinter as JSON + add an adapter controller.

Precision <seconds? Switch DateTimeFormatter pattern once.

# Bank‑Account Kata

Self‑contained project (JDK17, Maven). No frameworks, 100% unit‑tested, single in‑memory repository.

All methods now include concise Javadoc‑style comments so a reviewer quickly understands the intent.

