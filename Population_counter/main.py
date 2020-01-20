'''
Members of a population are represented as a collection of lifespans. Each lifespan represents a year of birth and, if the individual has died, a year of death.
write a function or method that takes the above information and returns every year in which the population was lower than the preceding year.


The solution has been implemented using Python Programming language and uses nested list to read the input data, builds a dictionary with population count for 
each year and then prints the year where population was lower than the previous year

'''
def population_calculator() : 
    
    curr_population = 0
    population_by_yr_dict = {}
    low_population_yr =[]
    
    # Input Nested list [birth year , death year]
    lifespan_list = [[1902,1991],
                    [1941,1978],
                    [2004,''],
                    [1957,''],
                    [1989,2008],
                    [1909,2005],
                    [1918,''],
                    [1913,2010],
                    [1979,''],
                    [1961,2002],
                    [1977,2003],
                    [1909,1991]
                    ]

    list_birth_year = [ i[0] for i in lifespan_list]

    list_death_year = [i[1] for i in lifespan_list]
    while '' in list_death_year : list_death_year.remove('')
    
    start_yr = min(min(list_birth_year), min(list_death_year))
    end_yr = max(max(list_birth_year), max(list_death_year))
    
    # Iterating through min and max year in the list and updating the current population count based on births and deaths
    for i in range(start_yr - 1 ,end_yr + 2):
        if i in list_birth_year:
            curr_population = curr_population + list_birth_year.count(i)
        elif i - 1 in list_death_year :
            curr_population = curr_population - list_death_year.count(i-1)
            
        population_by_yr_dict[i] = curr_population
        
    # Comparing current population count for each consecutive years in the dictionary
    for x in population_by_yr_dict :
        if x >= start_yr :
            if population_by_yr_dict[x] < population_by_yr_dict[x - 1]:
                low_population_yr.append(x)
   
    print ("List of Years where population was less than the previous year ：{}".format(low_population_yr))
 
population_calculator()