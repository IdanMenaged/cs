"""
Idan Menaged
"""


def main():
    pass


class Shape:
    def __init__(self, color, area, perimeter):
        self.color = color
        self.area = area
        self.perimeter = perimeter

    # setters
    def set_color(self, color):
        self.color = color

    def set_area(self, area):
        self.area = area

    def set_perimeter(self, perimeter):
        self.perimeter = perimeter

    # getters
    def get_color(self):
        return self.color

    def get_area(self):
        return self.area

    def get_perimeter(self):
        return self.perimeter

    def __repr__(self):
        return f'color: {self.color}, area: {self.area}, perimeter: {self.perimeter}'


if __name__ == '__main__':
    main()
