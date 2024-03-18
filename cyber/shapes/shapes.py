"""
Idan Menaged
"""


from shp import Shape
from math import pi


class Square(Shape):
    def __init__(self, color, side):
        super().__init__(color, side ** 2, side * 4)
        self.side = side

    def __repr__(self):
        return super().__repr__() + f', side: {self.side}'

    def who_am_i(self):
        return 'I am a square'
        
        
class Rectangle(Shape):
    def __init__(self, color, side1, side2):
        super().__init__(color, side1 * side2, 2 * (side1 + side2))
        self.side1, self.side2 = side1, side2

    def __repr__(self):
        return super().__repr__() + f', side1: {self.side1}, side2: {self.side2}'

    def who_am_i(self):
        return 'I am a rectangle'
        
        
class Circle(Shape):
    def __init__(self, color, radius):
        super().__init__(color, pi * radius ** 2, 2 * pi * radius)
        self.radius = radius

    def __repr__(self):
        return super().__repr__() + f', radius: {self.radius}'

    def who_am_i(self):
        return 'I am a circle'
