INSERT INTO kommune (kode, navn) VALUES
(630, 'Vejle'),
(851, 'Aalborg'),
(230, 'Rudersdal');

INSERT INTO sogn (kode, navn, kommune_id, dato_for_nedlukning, smittetryk) VALUES
(7975, 'Givskud', 1, TO_DATE('03/06/2021', 'DD/MM/YYYY'), 2.1),
(8364, 'Budolfi', 2, TO_DATE('30/05/2021', 'DD/MM/YYYY'), 1.8),
(8366, 'Vor Frelsers', 2, TO_DATE('24/05/2021', 'DD/MM/YYYY'), 1.5),
(8368, 'Vor Frue', 2, TO_DATE('04/06/2021', 'DD/MM/YYYY'), 1.1),
(9310, 'Høsterkøb', 3, TO_DATE('29/05/2021', 'DD/MM/YYYY'), 0.9);