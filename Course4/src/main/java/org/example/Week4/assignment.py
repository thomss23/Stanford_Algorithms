import random
import itertools
import math


# Papadimitriou algorithm

# Repeat log2n times:
#  - choose random initial choose_random_initial_assignment
#  - repeat 2n^2 times:
#     - if current assignment satisfies all clauses, halt+report this
#     - else, pick arbitrary unsatified clause and flip one of its vaiables [ choose between the two uniformly at random]
# - report Unsatisfiable


def papadimitriou_local_search_2sat(variables, clauses):
    n = len(clauses)
    log2n = math.log2(n)
    for i in range(log2n):
        variables = choose_random_initial_assignment(variables)
        for j in range(2 * n * n):
            if is_assignment_satisfying_clauses(variables, clauses):
                return True
            else:
                unsatisfiable_clause = pick_unsatisfiable_clause(variables, clauses)
                variables = flip_random_variable_from_unsatisfiable_clause(variables, unsatisfiable_clause)

    return False


def read_clauses_from_file(file_name):
    variables = {}
    clauses = []

    with open(file_name) as f:
        for line in f:
            split1 = line.split()
            if len(split1) == 1:
                variables = dict(enumerate(itertools.repeat(False, int(line) + 1)))
            else:
                split = line.split()
                var1 = int(split[0])
                var2 = int(split[1])
                clauses.append((var1, var2))
    return (variables, clauses)


def choose_random_initial_assignment(variables):
    for key in variables:
        variables[key] = random.choice([True, False])

    return variables


def is_assignment_satisfying_clauses(variables, clauses):

    for clause in clauses:
        var1, var2 = clause

        if var1 < 0:
            var1_to_check = not variables[abs(var1)]
        else:
            var1_to_check = variables[var1]

        if var2 < 0:
            var2_to_check = not variables[abs(var2)]
        else:
            var2_to_check = variables[var2]

        if var1_to_check or var2_to_check:
            continue
        else:
            return False
    return True


def pick_unsatisfiable_clause(variables, clauses):
    unsatisfiable_clauses = []

    for clause in clauses:
        var1, var2 = clause

        if var1 < 0:
            var1_to_check = not variables[abs(var1)]
        else:
            var1_to_check = variables[var1]

        if var2 < 0:
            var2_to_check = not variables[abs(var2)]
        else:
            var2_to_check = variables[var2]

        if var1_to_check or var2_to_check:
            unsatisfiable_clauses.append(clause)

    return random.choice(unsatisfiable_clauses)


def flip_random_variable_from_unsatisfiable_clause(variables, unsatisfiable_clause):
    var1, var2 = unsatisfiable_clause

    variable_to_flip = random.choice([var1, var2])

    variables[variable_to_flip] = not variables[abs(variable_to_flip)]

    return variables


def remove_unnecessary_clauses(clauses):
    while True:
        # Create dictionaries to track variable appearances as negated or not
        variable_negations = {}

        # Iterate through the clauses and record variable negations
        for var1, var2 in clauses:
            variable_negations[abs(var1)] = variable_negations.get(abs(var1), set())
            variable_negations[abs(var2)] = variable_negations.get(abs(var2), set())

            if var1 < 0:
                variable_negations[abs(var1)].add(False)
            else:
                variable_negations[abs(var1)].add(True)

            if var2 < 0:
                variable_negations[abs(var2)].add(False)
            else:
                variable_negations[abs(var2)].add(True)

        removable_vars = set()

        # Find variables that are either always negated or never negated
        for var, negations in variable_negations.items():
            if len(negations) == 1:
                removable_vars.add(var)

        if not removable_vars:
            break

        # Remove clauses containing removable variables
        clauses = [clause for clause in clauses if not any(abs(var) in removable_vars for var in clause)]

    return clauses


variables, clauses = read_clauses_from_file("2sat3.txt")

clauses2 = remove_unnecessary_clauses(clauses)

print(len(clauses2))
#solution 101100
print(papadimitriou_local_search_2sat(variables, clauses2))
