/**
 *  REST API Tutorial
 *
 *  Copyright 2015 Matt Nohr
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *	  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
	name: "REST API Tutorial",
	namespace: "mrnohr",
	author: "Matt Nohr",
	description: "Example using REST APIs",
	category: "My Apps",
	iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
	iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
	iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
	section("Title") {
		input "door", "capability.contactSensor", multiple: true, required: true
	}
}

def installed() {
	log.debug "Installed with settings: ${settings}"

	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

	unsubscribe()
	initialize()
}

def initialize() {
	subscribe(door, "contact.open", contactOpenHandler)
}

def contactOpenHandler(evt) {
	def params = [
		uri:  'http://api.openweathermap.org/data/2.5/',
		path: 'weather',
		contentType: 'application/json',
		query: [q:'Minneapolis', mode: 'json']
	]
	try {
		httpGet(params) {resp ->
			log.debug "resp data: ${resp.data}"
			log.debug "humidity: ${resp.data.main.humidity}"
		}
	} catch (e) {
		log.error "error: $e"
	}
}
