"""
Idan Menaged
"""

import shapes
import random

POSSIBLE_SHAPES = {shapes.Rectangle, shapes.Square, shapes.Circle}
MIN_VAL, MAX_VAL = 1, 11  # range for random sizes
POSSIBLE_COLORS = {'red', 'green', 'blue'}


def main():
    global my_container
    my_container = ShapeContainer()
    my_container.generate(100)
    print("total area:", my_container.sum_areas())
    print("total perimeter:", my_container.sum_perimeters())
    print("colors:", my_container.count_colors())


class ShapeContainer:
    def __init__(self):
        self.shapes = []

    def generate(self, n):
        """
        :param n: number of shapes to generate
        creates n random shapes and stores them in self.shapes
        """
        for i in range(n):
            # choose shape
            shape = POSSIBLE_SHAPES.pop()
            POSSIBLE_SHAPES.add(shape)

            # init
            color = POSSIBLE_COLORS.pop()
            POSSIBLE_COLORS.add(color)
            match shape:
                case shapes.Square:
                    side = random.randrange(MIN_VAL, MAX_VAL)
                    self.shapes.append(shape(color, side))
                case shapes.Rectangle:
                    side1, side2 = random.randrange(MIN_VAL, MAX_VAL), random.randrange(MIN_VAL, MAX_VAL)
                    self.shapes.append(shape(color, side1, side2))
                case shapes.Circle:
                    radius = random.randrange(MIN_VAL, MAX_VAL)
                    self.shapes.append(shape(color, radius))
                case _:
                    raise Exception(f'no protocol for initiating shape {shape}. have you added it to POSSIBLE_SHAPES '
                                    f'without adding to this match statement?')

    def sum_areas(self):
        """
        sum areas of all shapes
        """
        sum = 0
        for shape in self.shapes:
            sum += shape.get_area()
        return sum

    def sum_perimeters(self):
        """
        sum perimeters of all shapes
        """
        sum = 0
        for shape in self.shapes:
            sum += shape.get_perimeter()
        return sum

    def count_colors(self):
        """
        :return: a dict of color:count
        """
        counts = dict.fromkeys(POSSIBLE_COLORS, 0)
        for shape in self.shapes:
            counts[shape.get_color()] += 1

        return counts


if __name__ == '__main__':
    main()
