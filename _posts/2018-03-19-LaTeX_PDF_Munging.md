---
layout: post
title: PDF munging with LaTeX
---

An aide-memoire for myself:

        \documentclass[a4paper]{article}
        \usepackage{graphicx,forloop}

        \begin{document}
        \pagestyle{empty}

        \newcounter{pdfpagenumber}
        \forloop{pdfpagenumber}{1}{\value{pdfpagenumber} < 115}&#123;%
        \raisebox{-225ex}[0ex][0ex]{\makebox[90ex]{\includegraphics[width=12in,page=\arabic{pdfpagenumber}]{mtms.pdf}}&#125;%
        \newpage%
        }

        \end{document}

