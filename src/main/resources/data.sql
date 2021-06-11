INSERT INTO kommune (kode, navn) VALUES
(630, 'Vejle'),
(851, 'Aalborg'),
(230, 'Rudersdal'),
(666, 'Helvede'),
(730, 'Randers'),
(746, 'Skanderborg');


INSERT INTO sogn (kode, navn, kommune_id, dato_for_nedlukning, smittetryk, nedlukket) VALUES
(7975, 'Givskud', 1, TO_DATE('03/06/2021', 'DD/MM/YYYY'), 2.1, true),
(8364, 'Budolfi', 2, TO_DATE('30/05/2021', 'DD/MM/YYYY'), 1.8, true),
(8366, 'Vor Frelsers', 2, TO_DATE('24/05/2021', 'DD/MM/YYYY'), 1.5, true),
(8368, 'Vor Frue', 2, TO_DATE('04/06/2021', 'DD/MM/YYYY'), 1.1, false),
(9310, 'Høsterkøb', 3, TO_DATE('29/05/2021', 'DD/MM/YYYY'), 0.9, false),
(8357, 'Hunstrup', 4, TO_DATE('29/05/2020', 'DD/MM/YYYY'), 1.9, true),
(2233, 'Gimming', 4, TO_DATE('11/07/2020', 'DD/MM/YYYY'), 1.5, true),
(8326, 'Lillevorde', 5, TO_DATE('11/02/2020', 'DD/MM/YYYY'), 1.2, true),
(1324, 'Storenos', 5, TO_DATE('11/12/2020', 'DD/MM/YYYY'), 1.6, true),
(9191, 'Glasvand', 5, TO_DATE('23/02/2020', 'DD/MM/YYYY'), 1.1, true),
(1627, 'Egedal', 6, TO_DATE('11/05/2020', 'DD/MM/YYYY'), 1.2, true),
(5627, 'Sejlflod', 6, TO_DATE('12/12/2020', 'DD/MM/YYYY'), 1.6, true),
(0231, 'Champagne', 6, TO_DATE('11/12/2020', 'DD/MM/YYYY'), 2.2, true);