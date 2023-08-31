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
    times_to_run = math.log(len(clauses), 2)

    while(times_to_run != 0):
        variables = choose_random_initial_assignment(variables)

        iterations = 2 * len(clauses) ** 2

        while(iterations != 0):

            if is_assignment_satisfying_clauses(variables, clauses):
                return "satisfiable"
            else:
                unsatisfiable_clause = pick_unsatisfiable_clause(variables, clauses)
                variables = flip_random_variable_from_unsatisfiable_clause(variables, unsatisfiable_clause)
            iterations = iterations - 1
        times_to_run = times_to_run - 1
    
    return "unsatisfiable"
                
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
        randBool = random.choice([True, False])
        variables[key] = randBool
    
    return variables

def is_assignment_satisfying_clauses(variables, clauses):
    var1_to_check = False
    var2_to_check = False

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

        if var1_to_check or var2_to_check :
            continue
        else:
            return False
    return True

def pick_unsatisfiable_clause(variables, clauses):
    unsatisfiable_clauses = []
    var1_to_check = False
    var2_to_check = False

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

        if var1_to_check or var2_to_check :
            unsatisfiable_clauses.append(clause)

    return random.choice(unsatisfiable_clauses)


def flip_random_variable_from_unsatisfiable_clause(variables, unsatisfiable_clause):
    var1, var2 = unsatisfiable_clause

    variable_to_flip = random.choice([var1,var2])

    variables[variable_to_flip] = not variables[abs(variable_to_flip)]

    return variables


variables, clauses = read_clauses_from_file("simple_test_case.txt")

print(papadimitriou_local_search_2sat(variables, clauses))
