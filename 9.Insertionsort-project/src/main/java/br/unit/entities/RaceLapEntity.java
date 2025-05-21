
package br.unit.entities;


// Init -------------------------------------------------------------------- //


/**
 * Essa classe representa uma gravação de uma volta de um piloto, armazenando: data, nome do piloto, nome da equipe e o tempo de volta.
 * @author 01101010-01110000
 * @param date Data da corrida
 * @param driverName Nome do piloto
 * @param team Nome da equipe
 * @param duration Tempo de volta
 * @since 1.0
 */
public record RaceLapEntity (String date, String driverName, String team, String duration) {}
