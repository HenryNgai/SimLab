REPLACE INTO `role` VALUES (0,'STUDENT');
REPLACE INTO `role` VALUES (1,'INSTRUCTOR');
REPLACE INTO solution (solution_name, flamability, heat_of_combustion, ph, radioactivity, temperature, volume, solution_id) values ('HCl', 2 , 30, 0, 4, 20, 30,1);
REPLACE INTO solution (solution_name, flamability, heat_of_combustion, ph, radioactivity, temperature, volume, solution_id) values ('NaCl', 2 , 50, 0, 4, 20, 30,2);
REPLACE INTO solution (solution_name, flamability, heat_of_combustion, ph, radioactivity, temperature, volume, solution_id) values ('NaOH', 2 , 20, 0, 4, 20, 30,3);
REPLACE INTO solution (solution_name, flamability, heat_of_combustion, ph, radioactivity, temperature, volume, solution_id) values ('H2O', 0 , 80, 7, 0, 0, 20,4);
REPLACE INTO solution (solution_name, flamability, heat_of_combustion, ph, radioactivity, temperature, volume, solution_id) values ('CH', 0 , 40, 3, 0, 0, 20,5);
REPLACE INTO solution (solution_name, flamability, heat_of_combustion, ph, radioactivity, temperature, volume, solution_id) values ('HOH', 0 , 30, 3, 0, 0, 20,6);
REPLACE INTO solution (solution_name, flamability, heat_of_combustion, ph, radioactivity, temperature, volume, solution_id) values ('CHI', 0 , 70, 1, 0, 0, 20,7);
REPLACE INTO solution (solution_name, flamability, heat_of_combustion, ph, radioactivity, temperature, volume, solution_id) values ('CaO', 0 , 60,1, 0, 0, 20,8);
REPLACE INTO solution (solution_name, flamability, heat_of_combustion, ph, radioactivity, temperature, volume, solution_id) values ('COCL', 0 , 30, 3, 0, 0, 20,9);
REPLACE INTO container (container_name, capacity, weight, container_id) values ('Beaker', 150, 40, 5);
REPLACE INTO container (container_name, capacity, weight, container_id) values ('Flask', 250, 82, 6);
REPLACE INTO container (container_name, capacity, weight, container_id) values ('Pipette', 50 , 0.14, 7);
REPLACE INTO tool (tool_name, attribute1, attribute2, attribute3, description, tool_id) values ('Scale', "Weigh", null, null, "Weigh your thingies", 9);
REPLACE INTO tool (tool_name, attribute1, attribute2, attribute3, description, tool_id) values ('Bunsen Burner', null, null, null, "Make Things Hot", 10);
REPLACE INTO tool (tool_name, attribute1, attribute2, attribute3, description, tool_id) values ('Ice Bucket', null, null, null, "Make Things Cold", 11);

