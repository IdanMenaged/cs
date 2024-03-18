"""
    contacts test
    Ayelet Mashoah
"""
folder = "."
mods = ["shp", "shapes", "shp_container"]

import sys
import pytest
import importlib
import math

PATH_INDEX = 1
PATH_WITH_FOLDER_MIN_LEN = 1

SHAPE_CONTAINER_SIZE = 10

print("testing: ", [m for m in mods])

if len(sys.argv) > PATH_WITH_FOLDER_MIN_LEN:
    folder = sys.argv[PATH_INDEX]
# import tested modukes
sys_path = sys.path
sys.path.insert(0, folder)
for m in mods:
    try:
        vars()[m] = importlib.import_module(m)
    except Exception as e:
        print("Faile to load module m", e)

sys.path = sys_path

##############################################################################
#                         IOHandler class                                    #
##############################################################################


class IOHandler():
    """
    handles user input and output
    clollects holds lists of inputs and outputs which can be compared
    to the expected outputs for testin
    """
    def __init__(self, mod, inputs):
        """
        constructor - initiating inputs outputs and expected output
        overriding module add to module print and input methods
        which overrides general print and input methods
        """
        self.mod = mod
        self.inputs = inputs
        self.output = []
        # replacing print and input methods in correct_write
        mod.input = self.mock_input
        mod.print = self.mock_print

    def mock_input(self, s):
        """
        overrides input function for testing sake
        """
        # self.output.append(s)
        # self.output.append("\n")
        return self.inputs.pop(0)

    def mock_print(self, s1, s2=None, s3=None, s4=None, s5=None, end="\n"):
        """
        overrides print function testing sake
        """
        self.output.append(str(s1))
        # print("s1 = ", s1, self.output)
        if s2 is not None:
            # print("s2 = ", s2, self.output)
            self.output.append(" " + str(s2))
        if s3 is not None:
            # print("s3 = ", s3, self.output)
            self.output.append(" " + str(s3))
        if s4 is not None:
            # print("s4 = ", s4, self.output)
            self.output.append(" " + str(s4))
        if s5 is not None:
            # print("s5 = ", s5, self.output)
            self.output.append(" " + str(s5))
        self.output.append(end)


##############################################################################
#                               tests                                        #
##############################################################################



##############################################################################
#                               shp.py                                       #
##############################################################################
def test_constructor():
    """
    tests Shape class constructor
    input:
        color red
        area 10
        perimeter 20
    output:
        color red
        area 10
        perimeter 20
    """

    s = shp.Shape("red", 10, 20)
    assert s.color == "red" and s.area == 10 and s.perimeter == 20, \
            "expected: color = red area = 10 perimeter = 20\n" + \
            "while actutl: color = " + s.color + " area = "+ str(s.area)+ " perimeter = " + str(s.primeter)


def test_get_color():
    """
    tests Shape get_color
    input:
        color green
        area 5
        perimeter 7
    output:
        color green
    """
    s = shp.Shape("green", 5, 7)
    clr = s.get_color()
    assert clr == "green", "get_color failed - expected result: green"\
                    " while actual result: " + clr

def test_get_area():
    """
    tests Shape get_area
    input:
        color green
        area 5
        perimeter 7
    output:
        area 5
    """
    s = shp.Shape("green", 5, 7)
    area = s.get_area()
    assert area == 5, "get_area failed - expected result: 7"\
                    " while actual result: " + str(area)


def test_get_perimeter():
    """
    tests Shape get_perimeter
    input:
        color green
        area 5
        perimeter 7
    output:
        perimeter 7
    """
    s = shp.Shape("green", 5, 7)
    pr = s.get_perimeter()
    assert pr == 7, "get_area failed - expected result: 7"\
                    " while actual result: " + str(area)


def test_set_color():
    """
    tests Shape set_color
    input:
        color blue
        area 5
        perimeter 7
    output:
        color lue
    """
    s = shp.Shape("green", 5, 7)
    s.set_color("blue")
    assert s.color == "blue", "set_color failed - expected result: blues"\
                    " while actual result: " + s.clr

def test_set_area():
    """
    tests Shape get_area
    input:
        color green
        area 19
        perimeter 7
    output:
        area 5
    """
    s = shp.Shape("green", 5, 7)
    s.set_area(19)
    assert s.area == 19, "get_area failed - expected result: 7"\
                    " while actual result: " + str(s.area)


def test_set_perimeter():
    """
    tests Shape get_perimeter
    input:
        color green
        area 5
        perimeter 32
    output:
        perimeter 7
    """
    s = shp.Shape("green", 5, 7)
    s.set_perimeter(32)
    assert s.perimeter == 32, "get_area failed - expected result: 32"\
                    " while actual result: " + str(s.perimeter)


##############################################################################
#                               shpes.py                                     #
##############################################################################

############################## square ########################################
def test_square_constructor():
    """
    test Square constructor
    input:
      color: red
      side 2
    output:
      side: 2
      color: red
      area: 4
      perimeter: 4
    """
    s = shapes.Square("red", 2)
    assert s.side == 2 and s.color == "red" and \
           s.area == 4 and s.perimeter == 8, \
           "expeceted properties: side = 2 color = red " + \
           "area = 4 perimeter = 4 \n actual properties: " + \
           "side = "+ str(s.side) + \
           " color = " + s.color + \
           " area = " + str(s.area) +  " perimeter = " + str(s.perimeter)

def test_square_whoami():
    """
    test Square who_am_i
    input:
      None
    output:
      I am a square
    """
    s = shapes.Square("red", 2)
    assert s.who_am_i() == "I am a square", \
        "expected: I am a square while actual is: " + s.who_am_i()


def test_square_repr():
    """
    test Square __repr__
    input:
      None
    output:
      color: red, area: 4, perimeter: 8, side: 2
    """
    s = shapes.Square("red", 2)
    assert s.__repr__() == "color: red, area: 4, perimeter: 8, side: 2", \
        "expected: color: red, area: 4, perimeter: 8, side: 2" + \
            " actual: " + s.__repr__()


############################## rectangle #####################################
def test_rectangle_constructor():
    """
    test Rectangle constructor
    input:
      color: red
      side1 2
      side1 3
    output:
      side1: 2
      side1: 3
      color: red
      area: 6
      perimeter: 10
    """
    r = shapes.Rectangle("red", 2, 3)
    assert r.side1 == 2 and r.side2 == 3 and r.color == "red" and \
           r.area == 6 and r.perimeter == 10, \
           "expeceted properties: side1 = 2 side 2 = 3 color = red " + \
           "area = 6 perimeter = 10 \n actual properties: " + \
           "side1 = "+ str(r.side1)  + " side2 = "+ str(r.side2)  + \
           " color = " + r.color + \
           " area = " + str(r.area) +  " perimeter = " + str(r.perimeter)

def test_rectangle_whoami():
    """
    test Rectandle who_am_i
    input:
      None
    output:
      I am a rectangle
    """
    s = shapes.Rectangle("red", 2, 3)
    assert s.who_am_i() == "I am a rectangle", \
        "expected: I a am rectangle while actual is: " + s.who_am_i()


def test_rectangle_repr():
    """
    test rectangle __repr__
    input:
      None
    output:
      color: red, area: 6, perimeter: 10, side1: 2, side2: 3
    """
    r = shapes.Rectangle("red", 2, 3)
    assert r.__repr__() == "color: red, area: 6, perimeter: 10, side1: 2, side2: 3", \
        "expected: color: red, area: 6, perimeter: 10, side1: 2, side2: 3" + \
            " actual: " + r.__repr__()

############################## rectangle #####################################
def test_circle_constructor():
    """
    test Rectangle constructor
    input:
      color: red
      radiuus 2
      side1 3
    output:
      radius: 2
      color: red
      area: 12.56....
      perimeter: 12.56....
    """
    c = shapes.Circle("red", 2)
    area = math.pi * 4
    perimeter = math.pi * 4
    assert c.radius == 2 and c.area == math.pi * 4 and \
           c.perimeter == 4 * math.pi and  c.color == "red", \
           "expeceted properties: radius = 2 color = red " + \
           "area = " + str(area)+ " perimeter = " + str(perimeter) + \
           "\n actual properties: " + \
           "radius = "+ str(c.radius)  + \
           " color = " + c.color + \
           " area = " + str(c.area) +  " perimeter = " + str(c.perimeter)

def test_circle_whoami():
    """
    test Circle who_am_i
    input:
      None
    output:
      I am rectangle
    """
    s = shapes.Circle("red", 2)
    assert s.who_am_i() == "I am a circle", \
        "expected: I am a circle while actual is: " + s.who_am_i()

def test_circle_repr():
    """
    test circle __repr__
    input:
      None
    output:
      color: red, area: 4 pi, perimeter: 4 pi, radius: 2
    """
    c = shapes.Circle("red", 2)
    assert c.__repr__() == "color: red, area: " + str(4 * math.pi) + \
        ", perimeter: "+ str(4 * math.pi) + ", radius: 2", \
        "expected: color: red, area: " + str(4 * math.pi) + \
        ", perimeter: "+ str(4 * math.pi) + ", radius: 2" + \
            " actual: " + c.__repr__()

##############################################################################
#                          shp_container.py                                #
##############################################################################

def test_shape_container_constructor():
    """
    test shape container constructor
    input:
      None
    output:
      shape container object with a property shapes
      shapes value is []
    """
    shp_cntnr = shp_container.ShapeContainer()
    assert shp_cntnr.shapes == [], "expected shape container shapes property is []" + \
        "actual shape container shapes property is: " + shp_cntnr.shapes

def test_shape_container_list_len():
    """
    test shape container generated list len
    input:
      10
    output:
      shape container with 10 shapes
      - shapes chosen randomly
      - each shape basic properties(side/radius)are
                between 1 to 10 selcted randomly
      - olors are red green or blue
    """
    shp_cntnr = shp_container.ShapeContainer()
    shp_cntnr.generate(SHAPE_CONTAINER_SIZE)
    assert len(shp_cntnr.shapes) == SHAPE_CONTAINER_SIZE, \
        "expected list len is 5 actuqaal is: " + str(len(shp_cntnr.shapes))


def test_shape_container_shapes_type():
    """
    test shape container generate list shapes
    input:
      10
    output:
      shape container with 10 shapes
      - shapes chosen randomly
      - each shape basic properties(side/radius)are
                between 1 to 10 selected randomly
    """
    shp_cntnr = shp_container.ShapeContainer()
    shp_cntnr.generate(SHAPE_CONTAINER_SIZE)
    count = 0
    for s in shp_cntnr.shapes:
        if type(s) not in [shapes.Square, shapes.Rectangle, shapes.Circle]:
            count += 1
    assert count == 0, str(count) + \
                       " shapes are not Square or Circle or Rectangle"


def test_shape_container_shapes_side():
    """
    test shape container generate list shapes
    input:
      10
    output:
      shape container with 5 shapes
      - shapes chosen randomly
      - each shape basic properties(side/radius)are
                between 1 to 10 selcted randomly
    """
    shp_cntnr = shp_container.ShapeContainer()
    shp_cntnr.generate(SHAPE_CONTAINER_SIZE)
    count = 0
    for s in shp_cntnr.shapes:
        if isinstance(s, shapes.Square) and s.side not in range(1,11):
            count += 1
        elif isinstance(s, shapes.Rectangle) and \
                (s.side1 not in range(1,11) or s.side2 not in range(1,11)):
            count += 1
        elif isinstance(s, shapes.Circle) and s.radius not in range(1,11):
            count += 1
    assert count == 0, str(count) + \
                       "shapes side is not between 1 and 10"

def test_shape_container_shapes_color():
    """
    test shape container generate list shapes
    input:
      10
    output:
      shape container with 5 shapes
      - shapes chosen randomly
      - each shape basic properties(side/radius)are
                between 1 to 10 selcted randomly
    """
    shp_cntnr = shp_container.ShapeContainer()
    shp_cntnr.generate(SHAPE_CONTAINER_SIZE)
    count = 0
    for s in shp_cntnr.shapes:
        if s.color not in ["blue", "green", "red"]:
            count += 1
    assert count == 0, str(count) + \
                       "shapes color is not red green or blue while it should"

def test_sum_area():
    """
    test shape container summarizes areas correctly
    input:
      10
    output:
      sum of the 10 shapes areas
    """
    shp_cntnr = shp_container.ShapeContainer()
    shp_cntnr.generate(SHAPE_CONTAINER_SIZE)
    expected = sum([s.area for s in shp_cntnr.shapes])
    actual = shp_cntnr.sum_areas()
    assert expected == actual, \
            "expected sum areas is: " + str(expected) + \
            "\nwhile actual is: " + str(actual)


def test_sum_perimeter():
    """
    test shape container summarizes perimeters correctly
    input:
      10
    output:
      sum of the 10 shapes perimeter
    """
    shp_cntnr = shp_container.ShapeContainer()
    shp_cntnr.generate(SHAPE_CONTAINER_SIZE)
    expected = sum([s.perimeter for s in shp_cntnr.shapes])
    actual = shp_cntnr.sum_perimeters()
    assert expected == actual, \
            "expected sum perimeters is: " + str(expected) + \
            "\nwhile actual is: " + str(actual)


def test_count_colors():
    """
    test shape container summarizes colors correctly
    input:
      10
    output:
      dictionay with red green blue as keys \
      and shape count of each color as values
    """
    shp_cntnr = shp_container.ShapeContainer()
    shp_cntnr.generate(SHAPE_CONTAINER_SIZE)
    colors = shp_cntnr.count_colors()
    reds = len([s for s in shp_cntnr.shapes if s.color == "red"])
    greens = len([s for s in shp_cntnr.shapes if s.color == "green"])
    blues = len([s for s in shp_cntnr.shapes if s.color == "blue"])
    colors_dict = dict(colors)
    assert (reds == 0 or colors_dict["red"] == reds) and \
           (blues == 0 or colors_dict["blue"] == blues) and \
           (greens == 0 or colors_dict["green"] == greens)


def test_main():
    """
    tests main output
    input:
        None
    output of the form:
         total area: sum areas
         total perimeter: sum perimeters
         colors: colors dictionary
    """
    shapes.my_container = None
    inputs = []

    io_handler = IOHandler(shp_container, inputs)

    shp_container.main()
    shp_cntnr = shp_container.my_container
    reds = len([s for s in shp_cntnr.shapes if s.color == "red"])
    greens = len([s for s in shp_cntnr.shapes if s.color == "green"])
    blues = len([s for s in shp_cntnr.shapes if s.color == "blue"])

    expected_colors = {("red",reds), ("green", greens), ("blue", blues) }
    actual_colors =dict(eval("".join(io_handler.output[-2])))

    ok = expected_colors == set(actual_colors.items())
    output = "".join(io_handler.output[:-3])


    expected_out = "total area: " + \
                   str(sum([s.area for s in shp_cntnr.shapes])) + \
                   "\ntotal perimeter: " + \
                   str(sum([s.perimeter for s in shp_cntnr.shapes])) + "\n"

    assert ok and expected_out == output, "program output should be: " + \
                                   expected_out + " while it is: " + \
                                   output + \
                                   "\nor colors dictionary shoduld be:\n" + \
                                   str(expected_colors) + \
                                    "\n while it is: \n" + \
                                    str(set(actual_colors.items()))


def main():
    """
    calling pytest module for the above methods
    """


    # pytest.main(["shapes - tst.py", "-v", "--tb=no"])
    # test_main()

    pytest.main(["shapes - tst.py"])


if __name__ == '__main__':
    main()
