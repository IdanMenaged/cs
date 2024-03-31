"""
Idan Menaged
"""

import wx

WIDTH, HEIGHT = 300, 250


class GUI(wx.Frame):
	def __init__(self):
		super().__init__(None, title='technician server', size=(WIDTH, HEIGHT))

	def InitUI(self):
		"""
		create initial ui
		"""

		# MUST be last
		self.Centre()
		self.show(True)
