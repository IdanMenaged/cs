"""
Idan Menaged
"""

import wx

WIDTH, HEIGHT = 300, 250


class GUI(wx.Frame):
	"""
	gui for client
	"""
	def __init__(self):
		"""
		constructor
		"""
		super().__init__(None, title='technician server', size=(WIDTH, HEIGHT))

	def InitUI(self):
		"""
		create initial ui
		"""

		# MUST be last
		self.Centre()
		self.show(True)
