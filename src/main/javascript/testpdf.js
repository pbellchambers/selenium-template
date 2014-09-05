var doc = new jsPDF("l", "mm", 'a5');

doc.setFont('times', 'normal');

doc.setFontStyle('bold');
doc.setFontSize(8);
doc.text(10, 10, 'This is a test PDF');
doc.text(50, 50, 'You are from: ');

doc.output('datauri');
